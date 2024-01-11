package com.javalec.admin;

import java.awt.EventQueue;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

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
	private JLabel lblNewLabel_1_2;
	private JLabel lblNewLabel_1_2_1;
	private JTextField tfEngName;

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
//			@Override
//			public void windowActivated(WindowEvent e) {
//				removeComboCategory();
//				getComboCategory();
//				clearColumn();
//			}
			@Override
			public void windowOpened(WindowEvent e) {
				removeComboCategory();
				getComboCategory();
				clearColumn();
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
			AddNewProduct.add(getLblNewLabel_1_2());
			AddNewProduct.add(getLblNewLabel_1_2_1());
			AddNewProduct.add(getTfEngName());
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
			tfProductName.setBounds(117, 63, 217, 21);
			tfProductName.setColumns(10);
		}
		return tfProductName;
	}
	private JTextField getTfProductPrice() {
		if (tfProductPrice == null) {
			tfProductPrice = new JTextField();
			tfProductPrice.setColumns(10);
			tfProductPrice.setBounds(117, 113, 217, 21);
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
	private JLabel getLblNewLabel_1_2() {
		if (lblNewLabel_1_2 == null) {
			lblNewLabel_1_2 = new JLabel("영문명 : ");
			lblNewLabel_1_2.setBounds(357, 66, 57, 15);
		}
		return lblNewLabel_1_2;
	}
	private JLabel getLblNewLabel_1_2_1() {
		if (lblNewLabel_1_2_1 == null) {
			lblNewLabel_1_2_1 = new JLabel("원");
			lblNewLabel_1_2_1.setBounds(359, 116, 57, 15);
		}
		return lblNewLabel_1_2_1;
	}
	private JTextField getTfEngName() {
		if (tfEngName == null) {
			tfEngName = new JTextField();
			tfEngName.setColumns(10);
			tfEngName.setBounds(414, 60, 217, 21);
		}
		return tfEngName;
	}
	// === Function ======
	// 카테고리 가져오기.
	private void getComboCategory() {
		ProductDAO productDAO = new ProductDAO();
		ArrayList<String> listCategory = productDAO.selectItem();
		
		for(int i=0; i<listCategory.size(); i++) {
			cbItem.addItem(listCategory.get(i));
		}
	}
	// 카테고리 초기화.
	private void removeComboCategory() {
		int cntCategory = cbItem.getItemCount();
		for(int i=0; i<cntCategory; i++) {
			cbItem.remove(i);
		}
	}
	// 컬럼 내용 지우기.
	private void clearColumn() {
		tfProductName.setText("");
		tfProductPrice.setText("");
		taDetail.setText("");
		taNutritional.setText("");
		taIngredient.setText("");
		tfImage.setText("");
		lblImage.setIcon(null);
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
		String engproName = tfEngName.getText().trim();
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
		ProductDAO productDAO = new ProductDAO(productName, engproName, productPrice, detail, nutritional, ingredient, input, engproName, item);	// 이미지 이름에 영어이름으로 넣어봅.
		boolean result = productDAO.insertProductAction();
//		System.out.println("1 : "+result);
		// 상품 등록시 Register에도 등록함. 
//		String proname, int regseq, String adminid, int stonum, String item, String regdate, String gubun
		boolean result1 = productDAO.insertRegisterAction(productName, item, "등록");
//		System.out.println("2 : "+result1);
		
		if(result == true && result1 == true) {
			JOptionPane.showMessageDialog(null,  tfProductName.getText() + " 상품이 등록되었습니다.");
			gotoProductList();
		}else {
			JOptionPane.showMessageDialog(null, "입력중 문제가 발생했습니다.");
		}
	}	//End of clearColumn()
	// 상풍목록으로 이동.
	private void gotoProductList(){
		this.setVisible(false);
		ProductList productList = new ProductList();
		productList.main(null);
	}
	// -----------------[[[ File ]]]]]]---------------------------------------------------

	private void filePath() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG", "PNG", "BMP", "GIF", "jpg", "png", "bmp", "gif");
		chooser.setFileFilter(filter);
		
		int ret = chooser.showOpenDialog(null);
		if(ret != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.");
			return;
		}
		String filePath = chooser.getSelectedFile().getPath();
		tfImage.setText(filePath);
		ImageIcon icon = new ImageIcon(filePath);
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		lblImage.setIcon(new ImageIcon(changeImg));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		
	}//filePath

}
