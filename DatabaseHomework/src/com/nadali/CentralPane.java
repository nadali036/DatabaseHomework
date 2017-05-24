package com.nadali;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class CentralPane extends BorderPane {
	
	private GridPane gr;
	private Label lblFirstName;
	private TextField tfFirstName;
	private Label lblLastName;
	private TextField tfLastName;
	private Label lblDateOfAdmission;
	private TextField tfDateOfAdmission;
	private Label lblIndexNum;
	private TextField tfIndexNum;
	private Label lblCity;
	private TextField tfCity;
	
	private UserEntity updateUser;

	private Button btnRead;
	private Button btnAdd;
	private Button btnUpdate;
	private Button btnDelete;

	private TableView<UserEntity> table =  new TableView<UserEntity>();
	private TableColumn< UserEntity, String> firstName;
	private TableColumn< UserEntity, String> lastName;
	private TableColumn< UserEntity, String> singIn;
	private TableColumn< UserEntity, String> indexNum;
	private TableColumn< UserEntity, String> city;

	public CentralPane() {

		this.gr = new GridPane();
		this.gr.setAlignment(Pos.CENTER_LEFT);
		this.gr.setPadding(new Insets(50));
		this.gr.setHgap(15);
		this.gr.setVgap(15);
		
		this.initLblAndTf();
		this.initBtn();
		this.initTableView();
		this.initBtnRead();
		this.initBtnAdd();
		this.initBtnDelete();
		this.initBtnUpdate();
	}

	public void initLblAndTf() {

		this.lblFirstName = new Label("Enter first name: ");
		this.gr.add(this.lblFirstName, 0, 0);

		this.tfFirstName = new TextField();
		this.gr.add(this.tfFirstName, 1, 0);

		this.lblLastName = new Label("Enter last name: ");
		this.gr.add(this.lblLastName, 0, 1);

		this.tfLastName = new TextField();
		this.gr.add(this.tfLastName, 1, 1);

		this.lblDateOfAdmission = new Label("Enter date of admission: ");
		this.gr.add(this.lblDateOfAdmission, 0, 2);

		this.tfDateOfAdmission = new TextField();
		this.gr.add(this.tfDateOfAdmission, 1, 2);

		this.lblIndexNum = new Label("Enter the number of index: ");
		this.gr.add(this.lblIndexNum, 0, 3);

		this.tfIndexNum = new TextField();
		this.gr.add(this.tfIndexNum, 1, 3);

		this.lblCity = new Label("Enter city name: ");
		this.gr.add(this.lblCity, 0, 4);

		this.tfCity = new TextField();
		this.gr.add(this.tfCity, 1, 4);

	}

	public void initBtn() {
		this.btnRead = new Button("Read");
		this.gr.add(this.btnRead, 0, 5);

		this.btnAdd = new Button("Add");
		this.gr.add(this.btnAdd, 1, 5);

		this.btnDelete = new Button("Delete");
		this.gr.add(this.btnDelete, 0, 6);

		this.btnUpdate = new Button("Update");
		this.gr.add(this.btnUpdate, 1, 6);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void initTableView() {
		this.table.setEditable(true);
		
		this.firstName = new TableColumn("First Name");
		this.firstName.setMinWidth(95);
		this.firstName.setCellValueFactory
			(new PropertyValueFactory<UserEntity, String>("firstName"));
		
		
		this.lastName = new TableColumn("Last Name");
		this.lastName.setMinWidth(95);
		this.lastName.setCellValueFactory
			(new PropertyValueFactory<UserEntity, String>("lastName"));
		
		this.singIn = new TableColumn("Date of Admission");
		this.singIn.setMinWidth(140);
		this.singIn.setCellValueFactory
			(new PropertyValueFactory<UserEntity, String>("singIn"));
		
		this.indexNum = new TableColumn("Index Num");
		this.indexNum.setMinWidth(85);
		this.indexNum.setCellValueFactory
			(new PropertyValueFactory<UserEntity, String>("indexNum"));
		
		this.city = new TableColumn("City");
		this.city.setMinWidth(85);
		this.city.setCellValueFactory
			(new PropertyValueFactory<UserEntity, String>("city"));
		
		

		this.table.getColumns().addAll(this.firstName, this.lastName, this.singIn, this.indexNum, this.city);
		this.setTop(gr);
		this.setBottom(table);
		
	}

	public void initBtnRead() {
		btnRead.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				refreshTable();
			}
		});
	}
	
	public void initBtnAdd() {
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String fn = tfFirstName.getText();
				String ln = tfLastName.getText();
				String si = tfDateOfAdmission.getText();
				String bri = tfIndexNum.getText();
				String s = tfCity.getText();
				
				if(fn.trim().equals("") || ln.trim().equals("") || si.trim().equals("") || bri.trim().equals("") || s.equals("")) {
					showInfoDialog("You didn't enter all required fields");
					return;
				}

				boolean userSaved = SaveData.saveData(fn, ln, si, bri, s);

				if(userSaved) {
					refreshTable();
					resetInputFeilds();
					
					showInfoDialog("User added successfully");
				} else {
					showErrorDialog("Problem occured while adding user!!!");
				}
			}
		});
	}
	public void initBtnDelete() {
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				UserEntity selectedUser = table.getSelectionModel().getSelectedItem();
				if(selectedUser == null) {
					showInfoDialog("You didn't select row for delete");
					return;
				}
				
				boolean userDeleted = DeleteData.deleteUser(selectedUser.getId().get());
				if(userDeleted) {
					refreshTable();
					showInfoDialog("User deleted successfully");
				} else {
					showErrorDialog("Problem occured while deleting user!!!");
				}
			}
		});
	}

	public void initBtnUpdate() {
		btnUpdate.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if (updateUser == null) {
					updateUser = table.getSelectionModel().getSelectedItem();
					if(updateUser == null) {
						showInfoDialog("You didn't select row for edit");
						return;
					}
					tfFirstName.setText(updateUser.getFirstName());
					tfLastName.setText(updateUser.getLastName());
					tfDateOfAdmission.setText(updateUser.getSingIn());
					tfIndexNum.setText(updateUser.getIndexNum());
					tfCity.setText(updateUser.getCity());
					
					btnUpdate.setText("Save");
					btnRead.setDisable(true);
					btnAdd.setDisable(true);
					btnDelete.setDisable(true);
				} else {
					updateUser.setFirstName(tfFirstName.getText());
					updateUser.setLastName(tfLastName.getText());
					updateUser.setSingIn(tfDateOfAdmission.getText());
					updateUser.setIndexNum(tfIndexNum.getText());
					updateUser.setCity(tfCity.getText());
					UpadateData.upadateData(updateUser);
					refreshTable();
					resetInputFeilds();
					updateUser = null;

					btnUpdate.setText("Update");
					btnRead.setDisable(false);
					btnAdd.setDisable(false);
					btnDelete.setDisable(false);
				}
			}
		});
	}
	
	private void refreshTable() {
		ObservableList<UserEntity> allUsers = ReadData.readMe();
		this.table.setItems(allUsers);
	}

	private void resetInputFeilds() {
		this.tfFirstName.setText("");
		this.tfLastName.setText("");
		this.tfDateOfAdmission.setText("");
		this.tfIndexNum.setText("");
		this.tfCity.setText("");
	}
	
	private void showErrorDialog(String text) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setTitle("Error Dialog");
		alert.setContentText(text);
		alert.showAndWait();
	}

	private void showInfoDialog(String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Information Dialog");
		alert.setContentText(text);
		alert.showAndWait();
	}
}
