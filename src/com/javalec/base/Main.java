package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.javalec.common.ShareVar;
import com.javalec.function.AdminDAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main {

	private JFrame frame;
	private JLabel lblNewLabel;
	private JTextField tfID;
	private JLabel lblPassword;
	private JButton btnLogin;
	private JPasswordField pfPW;
	private JCheckBox ckbxShowPW;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("관리자 로그인");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getLblNewLabel());
		frame.getContentPane().add(getTfID());
		frame.getContentPane().add(getLblPassword());
		frame.getContentPane().add(getBtnLogin());
		frame.getContentPane().add(getPfPW());
		frame.getContentPane().add(getCkbxShowPW());
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("ID : ");
			lblNewLabel.setBounds(115, 82, 31, 15);
		}
		return lblNewLabel;
	}
	private JTextField getTfID() {
		if (tfID == null) {
			tfID = new JTextField();
			tfID.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					char specialKey = e.getKeyChar();
					if (specialCharacter(specialKey)) {
						JOptionPane.showMessageDialog(null, "ID에 특수문자는 안됩니다.", "경고", JOptionPane.ERROR_MESSAGE);
						tfID.setText("");
					} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						pfPW.requestFocus();
					}
				}
			});
			tfID.setBounds(151, 79, 183, 21);
			tfID.setColumns(10);
		}
		return tfID;
	}
	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("Password : ");
			lblPassword.setBounds(69, 131, 77, 15);
		}
		return lblPassword;
	}
	private JCheckBox getCkbxShowPW() {
		if (ckbxShowPW == null) {
			ckbxShowPW = new JCheckBox("비밀번호 보기");
			ckbxShowPW.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					checkPassword();
				}
			});
			ckbxShowPW.setBounds(167, 155, 115, 23);
		}
		return ckbxShowPW;
	}
	private JButton getBtnLogin() {
		if (btnLogin == null) {
			btnLogin = new JButton("login");
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					loginAction();
				}
			});
			btnLogin.setBounds(185, 188, 97, 23);
		}
		return btnLogin;
	}
	
	private JPasswordField getPfPW() {
		if (pfPW == null) {
			pfPW = new JPasswordField();
			pfPW.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						loginAction();
					}
				}
			});
			pfPW.setBounds(151, 128, 183, 21);
		}
		return pfPW;
	}
	
	// ===============  Function =========================================== 
	private void loginAction() {
		// 입력 안했을시 어디를 안했는지 체크 받기
		int check = inputCheck();

		String id = tfID.getText();
		char[] charPw = pfPW.getPassword();
		String pw = new String(charPw);
		
		// 입력을 안했을시 공지 또는 빈칸
		if (check != 0 || (id.equals("") && (pw.equals("")))) {
			JOptionPane.showMessageDialog(null, "항목을 입력 하세요.");
			tfID.setText("");
			pfPW.setText("");
			tfID.requestFocus();
		} else {
			AdminDAO adminDao = new AdminDAO();
			boolean result = adminDao.loginCheckAction(id, pw); // 로그인 성공 여부 확인

			if (result) {
				// 로그인 성공시
				ShareVar.loginID = id; 
				frame.setVisible(false);
//				frame.dispose();
//				Sales sales = new Sales();
//				sales.setVisible(true);
			} else {
				// 로그인 실패 시
				JOptionPane.showMessageDialog(null, "정보가 잘못 입력되었습니다. 다시 입력하여 주세요.");
				tfID.setText("");
				pfPW.setText("");
				tfID.requestFocus();
			}
		}
	}//end of loginAction
	
	private int inputCheck() {
		int checkResult = 0;
		char[] passwordChars = pfPW.getPassword();

		if (tfID.getText().trim().length() == 0) { // ID에 입력 안했을 때
			checkResult++;
			tfID.requestFocus();
		}
		if (passwordChars.length == 0) { // PW에 입력 안했을 때
			checkResult++;
			pfPW.requestFocus();
		}

		return checkResult;
	}

	private boolean specialCharacter(char specialKey) {
		// ID에 특수 문자 체크
		return "!@#$%^&*()-_=+`~/?,.<>{}[];:|\"'\\".indexOf(specialKey) != -1;
	}
	
	// 	비밀번호 보기 체크시 
	private void checkPassword() {
		if (ckbxShowPW.isSelected()) {
			pfPW.setEchoChar((char) 0); // 비밀번호 표시
		} else {
			pfPW.setEchoChar('\u2022'); // 비밀번호 숨김
		}
	}

}// END
