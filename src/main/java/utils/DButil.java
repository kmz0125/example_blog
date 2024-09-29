package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import constants.JpaConst;

public class DButil {

    private static EntityManagerFactory emf;

    //EntityManagerインスタンスを生成
    public static EntityManager createEntitiManager() {
        return _getEntityManagerFactory().createEntityManager();
    }

    //EntityManagerFactoryインスタンスを生成
    private static EntityManagerFactory _getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(JpaConst.PERSISTENCE_UNIT_NAME);
        }

        return emf;
    }

}
