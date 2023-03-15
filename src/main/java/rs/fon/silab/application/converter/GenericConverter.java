/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.fon.silab.application.converter;

import rs.fon.silab.application.dto.Dto;
import rs.fon.silab.application.model.Entity;

/**
 *
 * @author gg
 */
public interface GenericConverter <dto extends Dto, entity extends Entity>{
    entity toEntity(dto d);
    
    dto toDto(entity e);
    
}
