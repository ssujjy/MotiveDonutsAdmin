package com.javalec.admin;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.javalec.common.ShareVar;
import com.javalec.function.AdminDAO;
import com.javalec.function.ProductDAO;

import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ProductAdd extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel AddNewProduct;
	private JComboBox cbItem;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_1_1;
	private JLabel lblNewLabel_1_1_1;
	private JLabel lblNewLabel_1_1_1_1;
	private JLabel lblNewLabel_1_1_1_1_1;
	private JTextField tfProductName;
	private JTextField tfProductPrice;
	private JTextArea taDetail;
	private JTextArea taNutritional;
	private JTextArea taIngredient;
	private JLabel lblImage;
	private JTextField tfImage;
	private JButton btnImage;
	private JButton btnSave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductAdd frame = new ProductAdd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProductAdd() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				getComboCategory();
			}
		});
		setTitle("상품등록");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 985, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTabbedPane());
	}
	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(12, 10, 945, 493);
			tabbedPane.addTab("상품등록", null, getAddNewProduct(), null);
		}
		return tabbedPane;
	}
	private JPanel getAddNewProduct() {
		if (AddNewProduct == null) {
			AddNewProduct = new JPanel();
			AddNewProduct.setLayout(null);
			AddNewProduct.add(getCbItem());
			AddNewProduct.add(getLblNewLabel());
			AddNewProduct.add(getLblNewLabel_1());
			AddNewProduct.add(getLblNewLabel_1_1());
			AddNewProduct.add(getLblNewLabel_1_1_1());
			AddNewProduct.add(getLblNewLabel_1_1_1_1());
			AddNewProduct.add(getLblNewLabel_1_1_1_1_1());
			AddNewProduct.add(getTfProductName());
			AddNewProduct.add(getTfProductPrice());
			AddNewProduct.add(getTaDetail());
			AddNewProduct.add(getTaNutritional());
			AddNewProduct.add(getTaIngredient());
			AddNewProduct.add(getLblImage());
			AddNewProduct.add(getTfImage());
			AddNewProduct.add(getBtnImage());
			AddNewProduct.add(getBtnSave());
		}
		return AddNewProduct;
	}
	private JComboBox getCbItem() {
		if (cbItem == null) {
			cbItem = new JComboBox();
			cbItem.setBounds(117, 23, 150, 23);
		}
		return cbItem;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("분류 : ");
			lblNewLabel.setBounds(32, 27, 57, 15);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("상품명 : ");
			lblNewLabel_1.setBounds(32, 66, 57, 15);
		}
		return lblNewLabel_1;
	}
	private JLabel getLblNewLabel_1_1() {
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("상품가격 : ");
			lblNewLabel_1_1.setBounds(32, 116, 76, 15);
		}
		return lblNewLabel_1_1;
	}
	private JLabel getLblNewLabel_1_1_1() {
		if (lblNewLabel_1_1_1 == null) {
			lblNewLabel_1_1_1 = new JLabel("설명 : ");
			lblNewLabel_1_1_1.setBounds(32, 161, 76, 15);
		}
		return lblNewLabel_1_1_1;
	}
	private JLabel getLblNewLabel_1_1_1_1() {
		if (lblNewLabel_1_1_1_1 == null) {
			lblNewLabel_1_1_1_1 = new JLabel("영양정보 : ");
			lblNewLabel_1_1_1_1.setBounds(32, 228, 76, 15);
		}
		return lblNewLabel_1_1_1_1;
	}
	private JLabel getLblNewLabel_1_1_1_1_1() {
		if (lblNewLabel_1_1_1_1_1 == null) {
			lblNewLabel_1_1_1_1_1 = new JLabel("성분 : ");
			lblNewLabel_1_1_1_1_1.setBounds(32, 303, 76, 15);
		}
		return lblNewLabel_1_1_1_1_1;
	}
	private JTextField getTfProductName() {
		if (tfProductName == null) {
			tfProductName = new JTextField();
			tfProductName.setBounds(117, 63, 432, 21);
			tfProductName.setColumns(10);
		}
		return tfProductName;
	}
	private JTextField getTfProductPrice() {
		if (tfProductPrice == null) {
			tfProductPrice = new JTextField();
			tfProductPrice.setColumns(10);
			tfProductPrice.setBounds(117, 113, 432, 21);
		}
		return tfProductPrice;
	}
	private JTextArea getTaDetail() {
		if (taDetail == null) {
			taDetail = new JTextArea();
			taDetail.setBounds(117, 156, 432, 47);
		}
		return taDetail;
	}
	private JTextArea getTaNutritional() {
		if (taNutritional == null) {
			taNutritional = new JTextArea();
			taNutritional.setBounds(117, 223, 432, 47);
		}
		return taNutritional;
	}
	private JTextArea getTaIngredient() {
		if (taIngredient == null) {
			taIngredient = new JTextArea();
			taIngredient.setBounds(117, 287, 432, 47);
		}
		return taIngredient;
	}
	private JLabel getLblImage() {
		if (lblImage == null) {
			lblImage = new JLabel("Image");
			lblImage.setHorizontalAlignment(SwingConstants.CENTER);
			lblImage.setBounds(12, 353, 96, 83);
		}
		return lblImage;
	}
	private JTextField getTfImage() {
		if (tfImage == null) {
			tfImage = new JTextField();
			tfImage.setEditable(false);
			tfImage.setBounds(117, 384, 288, 21);
			tfImage.setColumns(10);
		}
		return tfImage;
	}
	private JButton getBtnImage() {
		if (btnImage == null) {
			btnImage = new JButton("이미지 추가");
			btnImage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					filePath();
				}
			});
			btnImage.setBounds(417, 383, 132, 23);
		}
		return btnImage;
	}
	private JButton getBtnSave() {
		if (btnSave == null) {
			btnSave = new JButton("등록");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addNewProduct();
				}
			});
			btnSave.setBounds(646, 23, 97, 23);
		}
		return btnSave;
	}
	
	// === Function ======
	// 카테고리 가져오기.
	private void getComboCategory() {
		AdminDAO adminDAO = new AdminDAO();
		ArrayList<String> listCategory = adminDAO.selectItem();
		
		for(int i=0; i<listCategory.size(); i++) {
			cbItem.addItem(listCategory.get(i));
		}
	}
	
	// 필드 체크.
	private int insertFieldCheck() {
		int i = 0;
		
		if(tfProductName.getText().trim().length() == 0) {
			i++;
			tfProductName.requestFocus();
		}
		if(tfProductPrice.getText().trim().length() == 0) {
			i++;
			tfProductPrice.requestFocus();
		}
		if(taDetail.getText().trim().length() == 0) {
			i++;
			taDetail.requestFocus();
		}
		if(taNutritional.getText().trim().length() == 0) {
			i++;
			taNutritional.requestFocus();
		}
		if(taIngredient.getText().trim().length() == 0) {
			i++;
			taIngredient.requestFocus();
		}
		if(tfImage.getText().trim().length() == 0) {
			i++;
			tfImage.requestFocus();
		}
		return i;
	}//insertFieldCheck
	
	private void addNewProduct() {
		int i_chk = insertFieldCheck();
		if(i_chk == 0) {
			insertNewProduct();
		}else {
			JOptionPane.showMessageDialog(null, "데이터를 확인하세요");
		}
	}	//End of clearColumn()
	
	private void insertNewProduct() {
		String productName = tfProductName.getText().trim();
		int productPrice = Integer.parseInt(tfProductPrice.getText().trim().replace(",", ""));
		String detail = taDetail.getText().trim();
		String nutritional = taNutritional.getText().trim();
		String ingredient = taIngredient.getText().trim();
		String imageName = tfImage.getText().trim();
		String item = (String) cbItem.getSelectedItem();
		
		// Image File
		FileInputStream input = null;
		File file = new File(imageName);
		try {
			input = new FileInputStream(file);
			
		}catch(FileNotFoundException e) { 
			e.printStackTrace();
		}
		// proname, sellprice, detail, nutritional, ingredient, image, imagename, wItem
		ProductDAO productDAO = new ProductDAO(productName, productPrice, detail, nutritional, ingredient, input, imageName, item);
		boolean result = productDAO.insertProductAction();
		
		if(result == true) {
			JOptionPane.showMessageDialog(null,  tfProductName.getText() + " 삼품이 등록되었습니다.");
		}else {
			JOptionPane.showMessageDialog(null, "입력중 문제가 발생했습니다.");
		}
	}	//End of clearColumn()
	
	// -----------------[[[ File ]]]]]]---------------------------------------------------

	private void filePath() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG", "PNG", "BMP", "GIF", "jpg", "png", "bmp", "gifs");
		chooser.setFileFilter(filter);
		
		int ret = chooser.showOpenDialog(null);
		if(ret != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.");
			return;
		}
		String filePath = chooser.getSelectedFile().getPath();
		tfImage.setText(filePath);
		lblImage.setIcon(new ImageIcon(filePath));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		
	}//filePath
}
