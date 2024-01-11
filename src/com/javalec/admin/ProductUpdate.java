package com.javalec.admin;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Image;
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
import com.javalec.model.ProductDTO;

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

public class ProductUpdate extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel UpdateProduct;
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
	private JButton btnUpdate;
	private JLabel lblNewLabel_1_2;
	private JTextField tfEngName;
	private JLabel lblNewLabel_1_2_1;
//	public String proname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductUpdate frame = new ProductUpdate();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

//	public ProductUpdate(String proname) throws HeadlessException {
//		super();
//		this.proname = proname;
//	}
	/**
	 * Create the frame.
	 */
	public ProductUpdate() {
		addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowActivated(WindowEvent e) {
//				removeComboCategory();
//				getComboCategory();
//			}
			@Override
			public void windowOpened(WindowEvent e) {
				removeComboCategory();
				getComboCategory();
				loadDetailProduct(ShareVar.proname);
			}
		});
		setTitle("상품수정");
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
			tabbedPane.addTab("상품수정", null, getUpdateProduct(), null);
		}
		return tabbedPane;
	}
	private JPanel getUpdateProduct() {
		if (UpdateProduct == null) {
			UpdateProduct = new JPanel();
			UpdateProduct.setLayout(null);
			UpdateProduct.add(getCbItem());
			UpdateProduct.add(getLblNewLabel());
			UpdateProduct.add(getLblNewLabel_1());
			UpdateProduct.add(getLblNewLabel_1_1());
			UpdateProduct.add(getLblNewLabel_1_1_1());
			UpdateProduct.add(getLblNewLabel_1_1_1_1());
			UpdateProduct.add(getLblNewLabel_1_1_1_1_1());
			UpdateProduct.add(getTfProductName());
			UpdateProduct.add(getTfProductPrice());
			UpdateProduct.add(getTaDetail());
			UpdateProduct.add(getTaNutritional());
			UpdateProduct.add(getTaIngredient());
			UpdateProduct.add(getLblImage());
			UpdateProduct.add(getTfImage());
			UpdateProduct.add(getBtnImage());
			UpdateProduct.add(getBtnUpdate());
			UpdateProduct.add(getLblNewLabel_1_2());
			UpdateProduct.add(getTfEngName());
			UpdateProduct.add(getLblNewLabel_1_2_1());
		}
		return UpdateProduct;
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
			taDetail.setBounds(117, 156, 503, 47);
		}
		return taDetail;
	}
	private JTextArea getTaNutritional() {
		if (taNutritional == null) {
			taNutritional = new JTextArea();
			taNutritional.setBounds(117, 223, 503, 47);
		}
		return taNutritional;
	}
	private JTextArea getTaIngredient() {
		if (taIngredient == null) {
			taIngredient = new JTextArea();
			taIngredient.setBounds(117, 287, 503, 47);
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
	private JButton getBtnUpdate() {
		if (btnUpdate == null) {
			btnUpdate = new JButton("수정");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateProduct();
				}
			});
			btnUpdate.setBounds(646, 23, 97, 23);
		}
		return btnUpdate;
	}
	private JLabel getLblNewLabel_1_2() {
		if (lblNewLabel_1_2 == null) {
			lblNewLabel_1_2 = new JLabel("영문명 : ");
			lblNewLabel_1_2.setBounds(346, 66, 57, 15);
		}
		return lblNewLabel_1_2;
	}
	private JTextField getTfEngName() {
		if (tfEngName == null) {
			tfEngName = new JTextField();
			tfEngName.setColumns(10);
			tfEngName.setBounds(403, 60, 217, 21);
		}
		return tfEngName;
	}
	private JLabel getLblNewLabel_1_2_1() {
		if (lblNewLabel_1_2_1 == null) {
			lblNewLabel_1_2_1 = new JLabel("원");
			lblNewLabel_1_2_1.setBounds(348, 116, 57, 15);
		}
		return lblNewLabel_1_2_1;
	}
	// === Function ======
	// 카테고리 가져오기.
	private void getComboCategory() {
		ProductDAO prodcutDAO = new ProductDAO();
		ArrayList<String> listCategory = prodcutDAO.selectItem();
		
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
	// 상품수정화면으로 이동시 기본정보 로드.
	private void loadDetailProduct(String productName) {	
		System.out.println("ProductUpdate : proname  : "+productName);
		ProductDAO productDAO = new ProductDAO();
		ProductDTO productDTO = productDAO.loadDetailProduct(productName);
		
		tfProductName.setText(productDTO.getProname());
		tfEngName.setText(productDTO.getEngproname());
		tfProductPrice.setText(Integer.toString(productDTO.getSellprice()));
		taDetail.setText(productDTO.getDetail());
		taNutritional.setText(productDTO.getNutritional());
		taIngredient.setText(productDTO.getIngredient());
		tfImage.setText(productDTO.getImagename());
		// Image 처리 : fileName이 틀려야 보여주기가 가능.
		String filePath = productDTO.getImagename();
		ImageIcon icon = new ImageIcon("./"+filePath);
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(100,100, Image.SCALE_SMOOTH);
//		ImageIcon changeIcon = new ImageIcon(changeImg);
		lblImage.setIcon(new ImageIcon(changeImg));	
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		
		File file = new File(filePath);
		file.delete();
	}
	private void updateProduct() {
		int i_chk = insertFieldCheck();
		if(i_chk == 0) {
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
			ProductDAO productDAO = new ProductDAO(productName, engproName, productPrice, detail, nutritional, ingredient, input, imageName, item);
			boolean result = productDAO.updateProductAction();
			
			// 상품 등록시 Register에도 등록함. 
//			String proname, int regseq, String adminid, int stonum, String item, String regdate, String gubun
			boolean result1 = productDAO.insertRegisterAction(productName, item, "수정");
			
			if(result == true && result1 == true) {
				JOptionPane.showMessageDialog(null,  tfProductName.getText() + " 상품이 수정되었습니다.");
				gotoProductList();
			}else {
				JOptionPane.showMessageDialog(null, "입력중 문제가 발생했습니다.");
			}
		}else {
			JOptionPane.showMessageDialog(null, "데이터를 확인하세요");
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
