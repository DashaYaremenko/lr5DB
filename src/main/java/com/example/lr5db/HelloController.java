package com.example.lr5db;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;


public class HelloController {
    private static final String URL = "jdbc:mysql://localhost:3306/railwaystat";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1111";
    @FXML
    public TextField ID;
    @FXML
    public TextField LastName, FirstName, TypeDoc;
    @FXML
    public Button addButton, showButton, updateButton, deleteButton;
    @FXML
    private TableView<ClientClass> TableShow;
    @FXML
    private TableColumn<ClientClass, String> IdColumn;
    @FXML
    private TableColumn<ClientClass, String> LastNameColumn;
    @FXML
    private TableColumn<ClientClass, String> FirstNameColumn;
    @FXML
    private TableColumn<ClientClass, String> TypeDocColumn;

    @FXML
    private void AddButtonAction(ActionEvent event) {
        String Id = ID.getText();
        String LastN = LastName.getText();
        String FirstN = FirstName.getText();
        String TypeD = TypeDoc.getText();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO clients (ID, LastName, FirstName,TypeDoc) VALUES (?,?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, Id);
                preparedStatement.setString(2, LastN);
                preparedStatement.setString(3, FirstN);
                preparedStatement.setString(4, TypeD);
               int rowsAdd=preparedStatement.executeUpdate();
               if (rowsAdd>0){
                   System.out.println("Запис добавлено успішно");
                   ShowButtonAction(event);
               }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void ShowButtonAction(ActionEvent event) {
        ObservableList<ClientClass> dataList = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT*FROM clients";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String id = resultSet.getString("ID");
                    String lastName = resultSet.getString("LastName");
                    String firstName = resultSet.getString("FirstName");
                    String typeDoc = resultSet.getString("TypeDoc");
                    dataList.add(new ClientClass(id, lastName, firstName, typeDoc));
                }
            }
            IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            LastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            FirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            TypeDocColumn.setCellValueFactory(new PropertyValueFactory<>("typeDoc"));
            TableShow.setItems(dataList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void UpdateButtonAction(ActionEvent event) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String Id = ID.getText();
            String LastN = LastName.getText();
            String FirstN = FirstName.getText();
            String TypeD = TypeDoc.getText();
            String sql="UPDATE clients SET LastName=?, FirstName=?, TypeDoc=? WHERE ID=? ";
            try (PreparedStatement preparedStatement=connection.prepareStatement(sql)){
                preparedStatement.setString(4, Id);
                preparedStatement.setString(1, LastN);
                preparedStatement.setString(2, FirstN);
                preparedStatement.setString(3, TypeD);
              int rowsUpdate=preparedStatement.executeUpdate();
              if (rowsUpdate>0){
                  System.out.println("Запис оновлено успішно");
                  ShowButtonAction(event);
              }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void DeleteButtonAction(ActionEvent event) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String id = ID.getText();
            String sql = "DELETE FROM clients WHERE ID=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, id);
                int rowsDeleteT=preparedStatement.executeUpdate();
                if (rowsDeleteT>0){
                    System.out.println("Запис видалено успішно");
                    TableShow.getItems().clear();
                    ShowButtonAction(event);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}