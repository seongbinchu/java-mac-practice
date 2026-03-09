package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

public class LoginForm extends JFrame implements ActionListener{
	private JTextComponent tfId;
	private JTextComponent pfPw;
	
	private JButton btnLogin;
	private JButton btnJoin;
	
	private Vector<User> list;
	private boolean isUpdated;
	
	public LoginForm() {
		loadData();
		init();
		setDisplay();
		addListener();
		showFrame();
	}
	public void init() {
		tfId = LoginUtils.getTExtComponent(LoginUtils.TEXT);
		pfPw = LoginUtils.getTExtComponent(LoginUtils.PASSWORD);
		
		btnJoin = LoginUtils.getButton("join");
		btnLogin = LoginUtils.getButton("Login");
	}
	public void loadData() {
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(
					new File(LoginUtils.DIR,LoginUtils.FILE)
					);
			br = new BufferedReader(fr);
			
			list = new Vector<User>();
			
			String line = null;
			while( (line = br.readLine()) != null) {
					String uid = line;
					String upw = br.readLine();
					String uname = br.readLine();
					String unick = br.readLine();
					String ugender = br.readLine();
					list.add(new User(uid,upw,uname,unick,ugender));
			}
		}catch(FileNotFoundException e) {
			list = new Vector<User>();
			File dir = new File(LoginUtils.DIR).getAbsoluteFile();
			if(!dir.exists()) {
				dir.mkdir();
			}
		}catch(IOException e) {
			JOptionPane.showMessageDialog(this, "데이터 파일 로딩 실패");
		}finally {
			closeAll(br,fr);
		}
	}
	private void closeAll(Closeable... c) {
		for(Closeable temp : c) {
			try {
				temp.close();
			}catch(Exception e) {}
		}
	}
	
	public void setDisplay() {
		JPanel pnlText = new JPanel(new GridLayout(0,1));
		JPanel pnlInput = new JPanel(new GridLayout(0,1));
	
		pnlText.add(LoginUtils.getLabel("ID"));
		pnlText.add(LoginUtils.getLabel("Password"));
		
		JPanel pnlId = new JPanel();
		pnlId.add(tfId);
		JPanel pnlPw = new JPanel();
		pnlPw.add(pfPw);
		pnlInput.add(pnlId);
		pnlInput.add(pnlPw);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.add(btnLogin);
		pnlSouth.add(btnJoin);
		
		JPanel pnlMain = new JPanel(new BorderLayout());
		pnlMain.add(pnlText, BorderLayout.WEST);
		pnlMain.add(pnlInput, BorderLayout.CENTER);
		pnlMain.add(pnlSouth, BorderLayout.SOUTH);
		
		pnlMain.setBorder(new EmptyBorder(5,10,5,10));
		
		add(pnlMain, BorderLayout.CENTER);
	}
	
	private void addListener() {
		btnLogin.addActionListener(this);
		btnJoin.addActionListener(this);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				int result = JOptionPane.showConfirmDialog(
						LoginForm.this,
						"exit?",
						"qusetion",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE
				);
				if(result == JOptionPane.YES_OPTION) {
					if(isUpdated) {
						result = save();
					}
					if(result == JOptionPane.YES_OPTION) {
						System.exit(0);
					}
					
				}
			}
		});
	}
	private int save(){
		FileWriter fw = null;
		PrintWriter pw = null;
		int result = JOptionPane.YES_OPTION;
		try {
			fw = new FileWriter(
					new File(LoginUtils.DIR,LoginUtils.FILE)
			);
			pw = new PrintWriter(fw);
			
			for(User user : list) {
				pw.println(user.getUid());
				pw.println(user.getUpw());
				pw.println(user.getUname());
				pw.println(user.getUnick());
				pw.println(user.getUgender());
			}
			pw.flush();
		}catch(IOException e) {
			result = JOptionPane.showConfirmDialog(
				LoginForm.this,
				"error occurred(saving user list). do you want to exit?",
				"question",
				JOptionPane.YES_OPTION,
				JOptionPane.QUESTION_MESSAGE
			);
		}finally {
			closeAll(pw,fw);
		}
		return result;
	}
	private void showFrame() {
		setTitle("Login");
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == btnLogin) {
			JTextComponent input =null;
			String msg = "welcome!";
			User user = null;
			if(LoginUtils.isEmpty(tfId)) {
				msg="input your ID";
				input=tfId;
			}else {
				if(LoginUtils.isEmpty(pfPw)) {
				msg = "input yout password";
				input = pfPw;
				}else {
					String uid = tfId.getSelectedText();
					String upw = pfPw.getText();
					user = findUser(uid);
					if(user == null) {
						msg = "check your ID";
						input = tfId;
					}else {
						if(!upw.equals(user.getUpw())) {
							msg = "check your password!";
							input = pfPw;
						}
					}
				}
			}
			JOptionPane.showMessageDialog(this,msg,"Information",JOptionPane.INFORMATION_MESSAGE);
			if(input != null) {
				input.requestFocus();
			}else {
				clear();
				setVisible(false);
				new InformationForm(this,user);
			}
		}else {
			clear();
			setVisible(false);
			new JoinForm(this);
		}
	}
	private void clear() {
		tfId.setText("");
		pfPw.setText("");
	}
	public User findUser(String userId) {
		int idx = list.indexOf(new User(userId));
		if(idx>=0) {
			return list.get(idx);
		}else {
			return null;
		}
	}
	public void addUser(User user) {
		if(findUser(user.getUid())== null) {
			list.add(user);
			isUpdated = true;
		}
	}
	public void removeUser(User user) {
		list.remove(user);
		isUpdated = true;
	}
		
	public static void main(String[] args) {
	
		new LoginForm();
	}	
}
