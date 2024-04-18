package myessentials.exception;

import net.minecraft.util.IChatComponent;

import myessentials.localization.api.LocalManager;

public abstract class FormattedException extends RuntimeException {

    public final IChatComponent message;

    public FormattedException(String localizationKey, Object... args) {
        message = LocalManager.get(localizationKey, args);
    }
}
