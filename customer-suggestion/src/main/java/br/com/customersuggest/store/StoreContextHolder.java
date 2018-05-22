package br.com.customersuggest.store;

import org.springframework.util.Assert;

public class StoreContextHolder {

    private static final ThreadLocal<StoreContext> storeContext =
            new ThreadLocal<StoreContext>();


    public static final StoreContext getContext() {
        StoreContext context = storeContext.get();

        if (context == null) {
            context = createEmptyContext();
            storeContext.set(context);
        }

        return storeContext.get();
    }

    public static final void setContext(StoreContext context) {
        Assert.notNull(context, "Only non-null UserContext instances are permitted");
        storeContext.set(context);
    }

    public static final StoreContext createEmptyContext() {
        return new StoreContext();
    }
}
