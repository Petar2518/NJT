/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.silab.application.exception;

/**
 *
 * @author gg
 */
public class NoMutualLeaguesException extends ProjectException {

    private final Object object;

    public NoMutualLeaguesException(Object object, String message) {
        super(message);
        this.object = object;
    }

    public Object getObject() {
        return object;
    }
}
