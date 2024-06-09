/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.billing.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NumberMapping {

    private Integer status;
    private Integer TelcoType;
    private Integer TypeOfNum;
}
