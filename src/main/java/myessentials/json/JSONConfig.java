package myessentials.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

/**
 * An abstract class for all JSON configs.
 */
public abstract class JSONConfig<T> {

    /**
     * The path to the file used.
     */
    protected final String path;
    protected final Gson gson;
    protected Type gsonType;

    public JSONConfig(String path) {
        this.path = path;
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Initializes everything.
     */
    public void init() {
        File file = new File(path);

        File parent = file.getParentFile();
        if(!parent.exists() && !parent.mkdirs()){
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }

        if(!file.exists() || file.isDirectory())
            create();
        else {
            List<T> wrappers = read();
            update(wrappers);
        }
    }

    /**
     * Creates the file if it doesn't exist.
     */
    protected abstract List<T> create();

    /**
     * Writes the updated list back to the file.
     */
    public abstract void write(List<T> items);

    /**
     * Reads the file and deletes all invalid values and returns a List with all values being valid.
     */
    protected abstract List<T> read();

    /**
     * Updates the file so that it has the previous valid settings plus the missing ones.
     */
    protected abstract void update(List<T> items);
}
