/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.exception;

/**
 *
 * @author gg
 */
public class EntityExistsException extends ProjectException{
    private Object entity;

    public EntityExistsException(Object entity, String message) {
        super(message);
        this.entity = entity;
    }

    public Object getEntity() {
        return entity;
    }
    
}
