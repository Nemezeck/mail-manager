package com.bd.edu.co.mail_manager.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class MensajeRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public String updateMensaje(String idMensaje, String usuario, Map<String, Object> updateFields) {
        if (updateFields.isEmpty()) {
            throw new IllegalArgumentException("No fields provided for update.");
        }

        StringBuilder queryString = new StringBuilder("UPDATE MENSAJE SET ");

        int index = 0;
        for (String field : updateFields.keySet()) {
            if (index > 0) {
                queryString.append(", ");
            }
            queryString.append(field).append(" = :").append(field);
            index++;
        }

        queryString.append(" WHERE idmensaje = :idMensaje AND usuario = :usuario");

        Query query = entityManager.createNativeQuery(queryString.toString());

        for (Map.Entry<String, Object> entry : updateFields.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        query.setParameter("idMensaje", idMensaje);
        query.setParameter("usuario", usuario);

        query.executeUpdate();

        return updateFields.keySet().stream().collect(Collectors.joining(", "));
    }
}

