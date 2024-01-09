package com.javalec.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

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
	private JLabel lblNewLabel_2;
	private JTextField tfImage;
	private JButton btnImage;

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
			AddNewProduct.add(getLblNewLabel_2());
			AddNewProduct.add(getTfImage());
			AddNewProduct.add(getBtnImage());
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
			tfProductName.setBounds(117, 63, 150, 21);
			tfProductName.setColumns(10);
		}
		return tfProductName;
	}
	private JTextField getTfProductPrice() {
		if (tfProductPrice == null) {
			tfProductPrice = new JTextField();
			tfProductPrice.setColumns(10);
			tfProductPrice.setBounds(117, 113, 150, 21);
		}
		return tfProductPrice;
	}
	private JTextArea getTaDetail() {
		if (taDetail == null) {
			taDetail = new JTextArea();
			taDetail.setBounds(117, 156, 264, 47);
		}
		return taDetail;
	}
	private JTextArea getTaNutritional() {
		if (taNutritional == null) {
			taNutritional = new JTextArea();
			taNutritional.setBounds(117, 223, 264, 47);
		}
		return taNutritional;
	}
	private JTextArea getTaIngredient() {
		if (taIngredient == null) {
			taIngredient = new JTextArea();
			taIngredient.setBounds(117, 287, 264, 47);
		}
		return taIngredient;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("Image");
			lblNewLabel_2.setBounds(32, 353, 106, 83);
		}
		return lblNewLabel_2;
	}
	private JTextField getTfImage() {
		if (tfImage == null) {
			tfImage = new JTextField();
			tfImage.setBounds(117, 384, 230, 21);
			tfImage.setColumns(10);
		}
		return tfImage;
	}
	private JButton getBtnImage() {
		if (btnImage == null) {
			btnImage = new JButton("FIle");
			btnImage.setBounds(366, 383, 97, 23);
		}
		return btnImage;
	}
}
