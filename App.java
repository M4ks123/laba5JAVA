package com.mycompany.mywebapp;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;



/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */


	public void onModuleLoad() {
		// Створюємо FormPanel та вказуємо його на сервіс.
		final FormPanel form = new FormPanel();
		form.setAction("/myFormHandler");

// Встановлюємо метод відправлення POST та кодування multipart MIME.
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);

// Створюємо панель для розміщення елементів форми.
		VerticalPanel panel = new VerticalPanel();
		panel.setStyleName("formPanel"); // Додаємо стиль для панелі форми
		form.setWidget(panel);

// Створюємо TextBox для імені.
		final TextBox nameTextBox = new TextBox();
		nameTextBox.setName("name");
		nameTextBox.setStyleName("inputField"); // Додаємо стиль для текстового поля
		panel.add(new Label("Name:"));
		panel.add(nameTextBox);

// Створюємо TextBox для email.
		final TextBox emailTextBox = new TextBox();
		emailTextBox.setName("email");
		emailTextBox.setStyleName("inputField"); // Додаємо стиль для текстового поля
		panel.add(new Label("Email:"));
		panel.add(emailTextBox);

// Створюємо TextBox для телефону.
		final TextBox phoneTextBox = new TextBox();
		phoneTextBox.setName("phone");
		phoneTextBox.setStyleName("inputField"); // Додаємо стиль для текстового поля
		panel.add(new Label("Phone:"));
		panel.add(phoneTextBox);

// Створюємо TextBox для повідомлення.
		final TextBox messageTextBox = new TextBox();
		messageTextBox.setName("message");
		messageTextBox.setStyleName("inputField"); // Додаємо стиль для текстового поля
		panel.add(new Label("Message:"));
		panel.add(messageTextBox);

// Додаємо кнопку "Submit".
		Button submitButton = new Button("Submit", new ClickHandler() {
			public void onClick(ClickEvent event) {
				form.submit();
			}
		});
		submitButton.setStyleName("submitButton"); // Додаємо стиль для кнопки
		panel.add(submitButton);

// Додаємо обробник подій для форми.
		form.addSubmitHandler(new FormPanel.SubmitHandler() {
			public void onSubmit(SubmitEvent event) {
				// Тут можна виконати перевірку даних перед відправленням форми на сервер.
				if (nameTextBox.getText().isEmpty() || emailTextBox.getText().isEmpty() || phoneTextBox.getText().isEmpty() || messageTextBox.getText().isEmpty()) {
					Window.alert("Please fill in all fields");
					event.cancel();
				}
			}
		});
		form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			public void onSubmitComplete(SubmitCompleteEvent event) {
				// Обробляємо результат відправлення форми на сервер.
				String name = nameTextBox.getText();
				String email = emailTextBox.getText();
				String phone = phoneTextBox.getText();
				String message = messageTextBox.getText();

				// Виводимо дані у попап
				Window.alert("Name: " + name + "\nEmail: " + email + "\nPhone: " + phone + "\nMessage: " + message);

			}
		});

// Додаємо форму до div з ідентифікатором "formContainer".
		RootPanel formContainer = RootPanel.get("formContainer");
		formContainer.add(form);



//		final Button sendButton = new Button("Send to server");
//		final TextBox nameField = new TextBox();
//		nameField.setText("GWT User");
//		final Label errorLabel = new Label();
//		sendButton.addStyleName("sendButton bg-green-500 px-2 py-2 rounded-lg text-white ml-4");
//		// We can add style names to widgets
//		sendButton.addStyleName("sendButton");
//
//		// Add the nameField and sendButton to the RootPanel
//		// Use RootPanel.get() to get the entire body element
//		RootPanel.get("nameFieldContainer").add(nameField);
//		RootPanel.get("sendButtonContainer").add(sendButton);
//		RootPanel.get("errorLabelContainer").add(errorLabel);
//
//		// Focus the cursor on the name field when the app loads
//		nameField.setFocus(true);
//		nameField.selectAll();
//
//		// Create the popup dialog box
//		final DialogBox dialogBox = new DialogBox();
//		dialogBox.addStyleName("bg-blue-500 px-2 py-2 rounded-lg text-white");
//		dialogBox.setText("Remote Procedure Call");
//		dialogBox.setAnimationEnabled(true);
//		final Button closeButton = new Button("Close");
//		closeButton.getElement().setId("closeButton");
//		final Label textToServerLabel = new Label();
//		final HTML serverResponseLabel = new HTML();
//		VerticalPanel dialogVPanel = new VerticalPanel();
//		dialogVPanel.addStyleName("dialogVPanel");
//		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
//		dialogVPanel.add(textToServerLabel);
//		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
//		dialogVPanel.add(serverResponseLabel);
//		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
//		dialogVPanel.add(closeButton);
//		dialogBox.setWidget(dialogVPanel);
//
//		// Add a handler to close the DialogBox
//		closeButton.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				dialogBox.hide();
//				sendButton.setEnabled(true);
//				sendButton.setFocus(true);
//			}
//		});
//
//		// Create a handler for the sendButton and nameField
//		class MyHandler implements ClickHandler, KeyUpHandler {
//			/**
//			 * Fired when the user clicks on the sendButton.
//			 */
//			public void onClick(ClickEvent event) {
//				sendNameToServer();
//			}
//
//			/**
//			 * Fired when the user types in the nameField.
//			 */
//			public void onKeyUp(KeyUpEvent event) {
//				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
//					sendNameToServer();
//				}
//			}
//
//			/**
//			 * Send the name from the nameField to the server and wait for a response.
//			 */
//			private void sendNameToServer() {
//				// First, we validate the input.
//				errorLabel.setText("");
//				String textToServer = nameField.getText();
//				if (!FieldVerifier.isValidName(textToServer)) {
//					errorLabel.setText("Please enter at least four characters");
//					return;
//				}
//
//				// Then, we send the input to the server.
//				sendButton.setEnabled(false);
//				textToServerLabel.setText(textToServer);
//				serverResponseLabel.setText("");
//				greetingService.greetServer(textToServer,
//						new AsyncCallback<GreetingResponse>() {
//							public void onFailure(Throwable caught) {
//								// Show the RPC error message to the user
//								dialogBox
//										.setText("Remote Procedure Call - Failure");
//								serverResponseLabel
//										.addStyleName("serverResponseLabelError");
//								serverResponseLabel.setHTML(SERVER_ERROR);
//								dialogBox.center();
//								closeButton.setFocus(true);
//							}
//
//							public void onSuccess(GreetingResponse result) {
//								dialogBox.setText("Remote Procedure Call");
//								serverResponseLabel
//										.removeStyleName("serverResponseLabelError");
//								serverResponseLabel.setHTML(new SafeHtmlBuilder()
//										.appendEscaped(result.getGreeting())
//										.appendHtmlConstant("<br><br>I am running ")
//										.appendEscaped(result.getServerInfo())
//										.appendHtmlConstant(".<br><br>It looks like you are using:<br>")
//										.appendEscaped(result.getUserAgent())
//										.toSafeHtml());
//								dialogBox.center();
//								closeButton.setFocus(true);
//							}
//						});
//			}
//		}
//
//		// Add a handler to send the name to the server
//		MyHandler handler = new MyHandler();
//		sendButton.addClickHandler(handler);
//		nameField.addKeyUpHandler(handler);
		RootPanel headerPanel = RootPanel.get("header");
		headerPanel.getElement().setId("header");
		headerPanel.addStyleName("header");
		headerPanel.getElement().setInnerHTML("<h1 style='color: white' >Web-site for laba</h1>");






	}
}
