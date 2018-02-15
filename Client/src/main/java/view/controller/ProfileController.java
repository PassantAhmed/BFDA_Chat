/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author Passant
 */
public class ProfileController {
    @FXML private JFXTextField userFullName;
    @FXML private JFXTextField userName;
    @FXML private JFXTextField userEmail;
    @FXML private JFXPasswordField userPassword;
    @FXML private JFXToggleButton userGender;
    @FXML private JFXDatePicker userDob;
    @FXML private JFXComboBox userStatus;
    @FXML private JFXComboBox userCountry;
    @FXML private Label warningMessage;
    @FXML private JFXButton cancelBtn;
    @FXML private JFXButton saveChangesBtn;
}
