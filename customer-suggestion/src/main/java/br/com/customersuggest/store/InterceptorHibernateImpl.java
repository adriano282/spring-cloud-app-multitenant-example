package br.com.customersuggest.store;

import br.com.customersuggest.model.MultiTenant;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class InterceptorHibernateImpl extends EmptyInterceptor {

    private static final Logger log = LoggerFactory.getLogger(InterceptorHibernateImpl.class);

    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        log.debug("[delete] Updating the entity " + id + " with util information: " + StoreContextHolder.getContext().getStoreId());
        ((MultiTenant)entity).setStoreId(StoreContextHolder.getContext().getStoreId());
    }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        log.debug("[save] Updating the entity " + id + " with util information: " + StoreContextHolder.getContext().getStoreId());
        ((MultiTenant)entity).setStoreId(StoreContextHolder.getContext().getStoreId());
        return false;
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        log.debug("[flush-dirty] Updating the entity " + id + " with util information: " + StoreContextHolder.getContext().getStoreId());
        ((MultiTenant) entity).setStoreId(StoreContextHolder.getContext().getStoreId());

        return false;
    }
}
