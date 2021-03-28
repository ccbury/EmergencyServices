//Add the class to the GUI package
package GUI;

//Add required import statements
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import medicalservice.contactMedical;
import medicalservice.emergencyResponse;
import medicalservice.medicalEmergency;
import medicalservice.medicalGrpc;
import medicalservice.medicalGrpc.medicalBlockingStub;
import medicalservice.medicalGrpc.medicalStub;
import medicalservice.medicalResponse;
import policeservice.PDEmergency;
import policeservice.PDResponse;
import policeservice.contactPDRequest;
import policeservice.emergencyPDResponse;
import policeservice.policeGrpc;
import policeservice.policeGrpc.policeBlockingStub;
import policeservice.policeGrpc.policeStub;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//Create EmergencyClient class
public class EmergencyClient {
	// Create a ServiceInfo variable to store the variable for the services
	private static ServiceInfo serviceinfo;

	// Create the JFrame that will hold the GUI for the client application
	private JFrame frame;

	// Create the Stub variables that refer to the grpc class allowing access to the
	// servers
	private static policeBlockingStub blockingStub;
	private static policeStub asyncStub;

	private static medicalBlockingStub blockingStub2;
	private static medicalStub asyncStub2;

	private JTextField PoliceEmergencyTextField;
	private JTextField policeNameTextField;
	private JTextField policeSituationTextField;
	private JTextField policeAddressTextField;
	private JTextField policePriorityTextField;
	private JTextField medicalEmergencyTextField;
	private JTextField MedicalInfoTextField;

	// Main method that starts the client
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					// Create a new EmergencyClient object. This creates the GUI
					EmergencyClient window = new EmergencyClient();
					// Set the GUI to be visible
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				} // End try/Catch
			}// End run
		});// End EventQueue.invokeLater
	}// End main method

	// Create EmergencyClient
	public EmergencyClient() {
		//
		String service_type = "_http._tcp.local.";
		discoverService(service_type);
		String host = serviceinfo.getHostAddresses()[0];
		ManagedChannel managed = ManagedChannelBuilder.forAddress(host, 50051).usePlaintext().build();
		ManagedChannel managed2 = ManagedChannelBuilder.forAddress(host, 50053).usePlaintext().build();
		blockingStub = policeGrpc.newBlockingStub(managed2);
		asyncStub = policeGrpc.newStub(managed2);
		blockingStub2 = medicalGrpc.newBlockingStub(managed);
		asyncStub2 = medicalGrpc.newStub(managed);

		// Add required objects to the GUI
		guiInitialiser();
	}// End EmergencyClient

	// Create initialiser class that creates the JFrame for the GUI and sets some of
	// its basic settings
	private void guiInitialiser() {
		frame = new JFrame();
		frame.setTitle("Client - Service Controller");
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// call addUiObjects class that adds the fields that are required in the GUI
		addUiObjects(frame);
	}// End guiInitialiser class

	// Create the addUiObjects method that adds the required fields to the GUI
	private void addUiObjects(JFrame frame2) {
		// Add all of the required fields for the GUI to function in this section.
		// including positioning and initialisation
		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel policePanel = new JPanel(false);
		JPanel medicalPanel = new JPanel(false);
		tabbedPane.addTab("Police", null, policePanel, "Police Service");
		policePanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Emergency:");
		lblNewLabel.setBounds(10, 9, 75, 14);
		policePanel.add(lblNewLabel);

		PoliceEmergencyTextField = new JTextField();
		PoliceEmergencyTextField.setBounds(159, 6, 275, 20);

		policePanel.add(PoliceEmergencyTextField);
		PoliceEmergencyTextField.setColumns(20);

		JButton policeEmergencyButton1 = new JButton("Submit");

		policeEmergencyButton1.setBounds(380, 40, 89, 23);
		policePanel.add(policeEmergencyButton1);

		JTextPane textPanePoliceResponse1 = new JTextPane();
		textPanePoliceResponse1.setEditable(false);
		textPanePoliceResponse1.setBounds(10, 74, 459, 69);
		policePanel.add(textPanePoliceResponse1);

		JLabel lblNewLabel_1 = new JLabel("Police Department Server Response:");
		lblNewLabel_1.setBounds(10, 49, 459, 14);
		policePanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Name:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setBounds(10, 164, 110, 14);
		policePanel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Situation:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setBounds(10, 189, 110, 14);
		policePanel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Address:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_4.setBounds(10, 214, 110, 14);
		policePanel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Priority:");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5.setBounds(10, 239, 110, 14);
		policePanel.add(lblNewLabel_5);

		policeNameTextField = new JTextField();
		policeNameTextField.setBounds(130, 161, 304, 20);
		policePanel.add(policeNameTextField);
		policeNameTextField.setColumns(10);

		policeSituationTextField = new JTextField();
		policeSituationTextField.setBounds(130, 186, 304, 20);
		policePanel.add(policeSituationTextField);
		policeSituationTextField.setColumns(10);

		policeAddressTextField = new JTextField();
		policeAddressTextField.setBounds(130, 211, 304, 20);
		policePanel.add(policeAddressTextField);
		policeAddressTextField.setColumns(10);

		policePriorityTextField = new JTextField();
		policePriorityTextField.setBounds(130, 236, 304, 20);
		policePanel.add(policePriorityTextField);
		policePriorityTextField.setColumns(10);

		JButton policeEmergencyButton2 = new JButton("Submit");

		policeEmergencyButton2.setBounds(380, 270, 89, 23);
		policePanel.add(policeEmergencyButton2);

		JTextPane textPanePoliceResponse2 = new JTextPane();
		textPanePoliceResponse2.setEditable(false);
		textPanePoliceResponse2.setBounds(10, 304, 459, 100);
		policePanel.add(textPanePoliceResponse2);

		JLabel lblNewLabel_6 = new JLabel("Police Department Server Response:");
		lblNewLabel_6.setBounds(10, 279, 459, 14);
		policePanel.add(lblNewLabel_6);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 151, 459, 2);
		policePanel.add(separator);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		tabbedPane.addTab("Medical", null, medicalPanel, "Medical Service");
		medicalPanel.setLayout(null);

		JLabel lblNewLabel_7 = new JLabel("Emergency:");
		lblNewLabel_7.setBounds(10, 11, 83, 14);
		medicalPanel.add(lblNewLabel_7);

		medicalEmergencyTextField = new JTextField();
		medicalEmergencyTextField.setColumns(20);
		medicalEmergencyTextField.setBounds(153, 8, 275, 20);
		medicalPanel.add(medicalEmergencyTextField);

		JButton medicalEmergencyButton2 = new JButton("Submit");

		medicalEmergencyButton2.setBounds(380, 269, 89, 23);
		medicalPanel.add(medicalEmergencyButton2);

		JTextPane textPaneMedicalResponse1 = new JTextPane();
		textPaneMedicalResponse1.setEditable(false);
		textPaneMedicalResponse1.setBounds(10, 74, 459, 69);
		medicalPanel.add(textPaneMedicalResponse1);

		JLabel lblNewLabel_1_1 = new JLabel("Medical Department Server Response:");
		lblNewLabel_1_1.setBounds(10, 52, 459, 14);
		medicalPanel.add(lblNewLabel_1_1);

		JLabel infoLabel = new JLabel("Name:");
		infoLabel.setHorizontalAlignment(SwingConstants.LEFT);
		infoLabel.setBounds(10, 241, 110, 14);
		medicalPanel.add(infoLabel);

		MedicalInfoTextField = new JTextField();
		MedicalInfoTextField.setColumns(10);
		MedicalInfoTextField.setBounds(124, 238, 304, 20);
		medicalPanel.add(MedicalInfoTextField);

		JButton medicalEmergencyButton1 = new JButton("Submit");
		medicalEmergencyButton1.setBounds(380, 40, 89, 23);
		medicalPanel.add(medicalEmergencyButton1);

		JTextPane textPaneMedicalResponse2 = new JTextPane();
		textPaneMedicalResponse2.setEditable(false);
		textPaneMedicalResponse2.setBounds(10, 303, 459, 100);
		medicalPanel.add(textPaneMedicalResponse2);

		JLabel lblNewLabel_6_1 = new JLabel("Medical Department Server Response:");
		lblNewLabel_6_1.setBounds(10, 273, 459, 14);
		medicalPanel.add(lblNewLabel_6_1);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 146, 459, 2);
		medicalPanel.add(separator_1);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_2);
		// End of adding all of the required fields to the GUI

		// Add an action listener for the Emergency submit button in PD tab
		policeEmergencyButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// If the Emergency field is empty do not submit to the server, instead display
				// an error message
				if (PoliceEmergencyTextField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Please enter your emergency..");
				} else {
					// Else send the request to the server and obtain the servers response to
					// display to the user
					contactPDRequest request = contactPDRequest.newBuilder().setText(PoliceEmergencyTextField.getText())
							.build();
					PDResponse response = blockingStub.contactPD(request);
					textPanePoliceResponse1.setText(response.getValue().toString());
				} // End if/else
			}// End action performed
		});// End action listener for emergency submit button
			// Add action listener for second button in PD tab
		policeEmergencyButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StreamObserver<emergencyPDResponse> responseObserver = new StreamObserver<emergencyPDResponse>() {
					@Override
					public void onNext(emergencyPDResponse value) {
						// textPanePoliceResponse2.setText(value.getName());
						if(!policeNameTextField.getText().isEmpty() && !policeAddressTextField.getText().isEmpty() && !policeSituationTextField.getText().isEmpty() && !policePriorityTextField.getText().isEmpty()) {
							textPanePoliceResponse2.setText("Name Recieved was: " + value.getName().toString()
								+ "\nAddress Received was: " + value.getAddress() + "\nSituation Received was: "
								+ value.getSituation() + "\nPriority Received was:" + value.getPriority());
						} else {
							JOptionPane.showMessageDialog(frame, "Please ensure all fields are completed...");
						}
					}// End onNext

					@Override
					public void onError(Throwable t) {
					}// End onError

					@Override
					public void onCompleted() {
					}// End onCompleted
				};// End of stream observer

				StreamObserver<PDEmergency> requestObserver = asyncStub.emergencyPD(responseObserver);
				try {
					String name = policeNameTextField.getText(), situation = policeSituationTextField.getText(),
							address = policeAddressTextField.getText(), priority = policePriorityTextField.getText();
					requestObserver.onNext(PDEmergency.newBuilder().setName(name).setAddress(address)
							.setPriority(priority).setSituation(situation).build());
					Thread.sleep(1000);
				} catch (RuntimeException | InterruptedException ioe) {
					ioe.printStackTrace();
				} // End try/catch
			}// End action performed
		});// End action listener for second button

		medicalEmergencyButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// If the Emergency field is empty do not submit to the server, instead display
				// an error message
				if (medicalEmergencyTextField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Please enter your emergency..");
				} else {
					// Else send the request to the server and obtain the servers response to
					// display to the user
					contactMedical request = contactMedical.newBuilder().setText(medicalEmergencyTextField.getText())
							.build();
					medicalResponse response = blockingStub2.contactMed(request);
					textPaneMedicalResponse1.setText(response.getValue().toString());
				} // End if/else

			}
		});

		ArrayList<String> tempArray = new ArrayList<>();
		medicalEmergencyButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StreamObserver<emergencyResponse> responseObserver = new StreamObserver<emergencyResponse>() {

					@Override
					public void onNext(emergencyResponse value) {
						textPaneMedicalResponse2.setText("Name Recieved was: " + value.getName().toString()
								+ "\nAddress Received was: " + value.getAddress() + "\nSituation Received was: "
								+ value.getInjury() + "\nPriority Received was:" + value.getPriority());
					}

					@Override
					public void onError(Throwable t) {
					}// End onError

					@Override
					public void onCompleted() {
					}// End onCompleted
				};
				StreamObserver<medicalEmergency> requestObserver = asyncStub2.emergencyDetail(responseObserver);
				try {
					if(tempArray.size() <=4) {
						if(tempArray.size() == 0) {
							infoLabel.setText("Injury:");
							tempArray.add(MedicalInfoTextField.getText());
							requestObserver.onNext(medicalEmergency.newBuilder().setText(MedicalInfoTextField.getText().toString()).build());
							MedicalInfoTextField.setText(null);
						}else if(tempArray.size() == 1) {
							infoLabel.setText("Address:");
							tempArray.add(MedicalInfoTextField.getText());
							requestObserver.onNext(medicalEmergency.newBuilder().setText(MedicalInfoTextField.getText().toString()).build());
							MedicalInfoTextField.setText(null);
						}else if(tempArray.size() == 2) {
							infoLabel.setText("Priority:");
							tempArray.add(MedicalInfoTextField.getText());
							requestObserver.onNext(medicalEmergency.newBuilder().setText(MedicalInfoTextField.getText().toString()).build());
							MedicalInfoTextField.setText(null);
						}else if(tempArray.size() == 3){
							infoLabel.setText("Name:");
							tempArray.add(MedicalInfoTextField.getText());
							requestObserver.onNext(medicalEmergency.newBuilder().setText(MedicalInfoTextField.getText().toString()).build());
							tempArray.clear();
							MedicalInfoTextField.setText(null);
						}//End if/else if
					}//End if
				} catch (RuntimeException ieo) {
					ieo.printStackTrace();
				} // End try/catch
			}//End action performed
		});//End action listener

		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
	}//End addUiObjects method

	// Create discoverService method that searches for services on the specified
	// ports and Ip's
	private void discoverService(String service_type) {
		// Try to find the service at the specified host ips and ports
		try {
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			jmdns.addServiceListener(service_type, new MyServiceListener());
			Thread.sleep(10000);
			jmdns.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // End try/catch
	}// End discoverService
		// Create new MyServiceListener class to allow the client to display when it has
		// connected to a service.
		// Implements service listener to allow finding of services

	private static class MyServiceListener implements ServiceListener {
		// create ServiceAdded method that displays a message that a service was added
		public void serviceAdded(ServiceEvent event) {
			System.out.println("Service added: " + event.getInfo());
		}// End serviceAdded
			// create ServiceRemoved method that displays a message that a service was
			// removed

		public void serviceRemoved(ServiceEvent event) {
			System.out.println("Service removed: " + event.getInfo());
		}// End serviceRemoved
			// create ServiceResolved method that displays a message that a service was
			// resolved

		public void serviceResolved(ServiceEvent event) {
			System.out.println("Service resolved: " + event.getInfo());
			serviceinfo = event.getInfo();
		}// End serviceResolved
	}// End MyServiceListener
}// End class
