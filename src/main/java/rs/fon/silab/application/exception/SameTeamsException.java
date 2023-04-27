/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.exception;

/**
 *
 * @author gg
 */
public class SameTeamsException extends ProjectException {

    private final Object entity;

    public SameTeamsException(Object entity, String message) {
        super(message);
        this.entity = entity;
    }

    public Object getEntity() {
        return entity;
    }

}
