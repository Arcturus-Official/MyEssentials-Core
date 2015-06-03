package mytown.core.chat;

import java.util.List;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import org.apache.commons.lang3.text.WordUtils;

public class HelpMenu {
    private int maxLines = 9;  // maxLines is 9 because 1 is taken up by the "top bar"
    private int numberOfPages = 0;
    private String name;
    private String[] lines;

    public HelpMenu(String name, String msg) {
        this(name, msg.split("\n"));
    }

    public HelpMenu(String name, String...lines) {
        this.name = name;
        this.lines = lines;
        numberOfPages = Math.round((float)lines.length/(float)maxLines);
    }

    public void setLines(String...lines) {
        this.lines = lines;
        numberOfPages = Math.round((float)lines.length/(float)maxLines);
    }

    public void setLines(List<String> lines) {
        if (this.lines == null) {
            this.lines = new String[]{};
        }
        setLines(lines.toArray(this.lines));
    }

    public void setMaxLines(int maxLines) {
        this.maxLines = maxLines;
        numberOfPages = lines.length/maxLines;
    }

    public int getMaxLines() {
        return maxLines;
    }

    /**
     * Sends the help page to the given sender
     */
    public void send(ICommandSender sender, int page) {
        if (page < 1)
			page = 1;
        if (page > numberOfPages)
			page = numberOfPages;
        int start = getMaxLines()*(page-1);

        sender.addChatMessage(new ChatComponentText(String.format("---------- %s Help (%s/%s) ----------", WordUtils.capitalizeFully(name), page, numberOfPages)));
        for (int i=0; start+i<lines.length && i<getMaxLines(); i++) {
            sender.addChatMessage(new ChatComponentText(lines[start+i]));
        }
    }
}