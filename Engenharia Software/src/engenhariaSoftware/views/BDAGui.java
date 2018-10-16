package engenhariaSoftware.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;
import engenhariaSoftware.common.Tweet;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.CardLayout;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.UIManager;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;

public class BDAGui extends JFrame {

	private JPanel contentPane;
	private JButton btnMyFeedTwitter;
	private JButton btnISCTEIULTwitter;
	private JTextField textSearchFieldTwitter;
	private JButton btnSearchTwitter;
	private DefaultListModel<String> model = new DefaultListModel<>();
	private JList listTwitter;
	private ArrayList<Tweet> listaTweets = new ArrayList<>();
	private JTextArea textAreaTweet;
	private JButton btnRetweet;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BDAGui frame = new BDAGui();
					frame.setVisible(true);
					frame.setSize(1009, 645);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BDAGui() {
		initComponents();
		try {
			createEventsTwitter();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This method contains all of the code for creating events
	 * @throws TwitterException 
	 *
	 */

	private void createEventsTwitter() throws TwitterException {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		
		cb.setDebugEnabled(true).setOAuthConsumerKey("x2pTILrYxrf7tE1r0dvuv9jWG").setOAuthConsumerSecret("I2elQ8AGRjfRme2f8PWG4OHwm0bCzOrYxvkzcmB00jJ8iXrvam").setOAuthAccessToken("1051429457913896962-KsecOb4dSYC2tY9YSfZKVnUgZilxyl").setOAuthAccessTokenSecret("4o63MCLz1VIMU6asHCa72cVppUdMcwsB40Y58u1pIk02p");
		
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter4j.Twitter twitter = tf.getInstance();
		
		twitter.getHomeTimeline();
		
		//get username, status
		
		List<Status> statusHome = twitter.getHomeTimeline();
		List<Status> statusIscte = twitter.getUserTimeline("iscteiul");
		
		
		btnMyFeedTwitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				textArea.setText("");
				model.clear();
				listaTweets.clear();
				for(Status st: statusHome) {
//					textArea.append(st.getUser().getName() + ": " + st.getText());
//					textArea.append("\n");
					System.out.println("Data: " + st.getCreatedAt());
					String user = st.getUser().getName();
					String text = st.getText();
					long id = st.getId();
					Date createdAt = st.getCreatedAt();
					Tweet tweet = new Tweet(user, text, id, createdAt);
//					model.addElement(tweet.getUserName() + tweet.getCreatedAt());
					model.addElement(tweet.confirmTweet());
					listaTweets.add(tweet);
					
				}	
			
			}
		});
		
		btnISCTEIULTwitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				textArea.setText("");
				model.clear();
				listaTweets.clear();
				for(Status st: statusIscte) {
//					textArea.append(st.getUser().getName() + ": " + st.getText());
//					textArea.append("\n");
//					model.addElement(st.getUser().getName() + ": " + st.getText());
					String user = st.getUser().getName();
					String text = st.getText();
					long id = st.getId();
					Date createdAt = st.getCreatedAt();
					Tweet tweet = new Tweet(user, text, id, createdAt);
//					model.addElement(tweet.getUserName() + tweet.getCreatedAt());
					model.addElement(tweet.confirmTweet());
					listaTweets.add(tweet);
				}	
			}
		});
		
		btnSearchTwitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				textArea.setText("");
				model.clear();
				listaTweets.clear();
				String searchTwitter = textSearchFieldTwitter.getText();
				Query query = new Query(searchTwitter);
				try {
					QueryResult result = twitter.search(query);
					List<Status> tweets = result.getTweets();
					for(Status st: tweets) {
//						textArea.append(t.getUser().getName() + ": " + t.getText());
//						textArea.append("\n");
//						model.addElement(t.getUser().getName() + ": " + t.getText());
						String user = st.getUser().getName();
						String text = st.getText();
						long id = st.getId();
						Date createdAt = st.getCreatedAt();
						Tweet tweet = new Tweet(user, text, id, createdAt);
//						model.addElement(tweet.getUserName() + tweet.getCreatedAt());
						model.addElement(tweet.confirmTweet());
						listaTweets.add(tweet);
					}

				} catch (TwitterException e1) {
					JOptionPane.showMessageDialog(null, "Introduza um valor válido", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		btnRetweet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(Tweet t: listaTweets) {
					if(textAreaTweet.getText().equals(t.getText())) {
						try {
							twitter.retweetStatus(t.getId());
							System.out.println("Retweet com sucesso!");
						} catch (TwitterException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		
		listTwitter.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				textAreaTweet.setText("");
				String selectedValue = (String) listTwitter.getSelectedValue();
				System.out.println(listaTweets);
				for(Tweet t: listaTweets) {
					System.out.println(t.getId());
					if(selectedValue != null && selectedValue.equals(t.confirmTweet())) {
						textAreaTweet.append(t.getUserName() + " | " + t.getCreatedAt());
						textAreaTweet.append("\n\n");
						textAreaTweet.append(t.getText());
					}
				}
			}
//				String selectedTweet = (String) listTwitter.getSelectedValue();
//				if(selectedTweet != null)
//					JOptionPane.showMessageDialog(null, selectedTweet, "Tweet", JOptionPane.INFORMATION_MESSAGE);
//			}
			
		});
		
	}

	/**
	 * This method contains all of the code for creating events
	 * and initializing components
	 *
	 */
	private void initComponents() {
		setTitle("Bom Dia Academia");
		setIconImage(Toolkit.getDefaultToolkit().getImage(BDAGui.class.getResource("/engenhariaSoftware/resources/IGE.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 428, Short.MAX_VALUE)
		);
		
		JPanel panelHome = new JPanel();
		panelHome.setBackground(Color.WHITE);
		tabbedPane.addTab("Home", null, panelHome, null);
		
		JLabel lblNewLabel_6 = new JLabel("Bom Dia Academia!");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JTextArea txtrdisclaimerEsta = new JTextArea();
		txtrdisclaimerEsta.setBackground(Color.WHITE);
		txtrdisclaimerEsta.setLineWrap(true);
		txtrdisclaimerEsta.setWrapStyleWord(true);
		txtrdisclaimerEsta.setText("**Disclaimer**\r\n\r\nEsta \u00E9 uma demonstra\u00E7\u00E3o daquilo em que deve consistir o trabalho de Engenharia de Software I.\r\n\r\nN\u00E3o \u00E9, nem de perto o produto final.");
		txtrdisclaimerEsta.setEditable(false);
		GroupLayout gl_panelHome = new GroupLayout(panelHome);
		gl_panelHome.setHorizontalGroup(
			gl_panelHome.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHome.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelHome.createParallelGroup(Alignment.LEADING)
						.addComponent(txtrdisclaimerEsta, GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
						.addComponent(lblNewLabel_6))
					.addContainerGap())
		);
		gl_panelHome.setVerticalGroup(
			gl_panelHome.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHome.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_6)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtrdisclaimerEsta, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(204, Short.MAX_VALUE))
		);
		panelHome.setLayout(gl_panelHome);
		
		JPanel panelFacebook = new JPanel();
		panelFacebook.setBackground(new Color(30, 144, 255));
		tabbedPane.addTab("Facebook", null, panelFacebook, null);
		GroupLayout gl_panelFacebook = new GroupLayout(panelFacebook);
		gl_panelFacebook.setHorizontalGroup(
			gl_panelFacebook.createParallelGroup(Alignment.LEADING)
				.addGap(0, 733, Short.MAX_VALUE)
		);
		gl_panelFacebook.setVerticalGroup(
			gl_panelFacebook.createParallelGroup(Alignment.LEADING)
				.addGap(0, 398, Short.MAX_VALUE)
		);
		panelFacebook.setLayout(gl_panelFacebook);
		
		JPanel panelTwitter = new JPanel();
		panelTwitter.setBackground(new Color(176, 224, 230));
		tabbedPane.addTab("Twitter", null, panelTwitter, null);
		
		btnMyFeedTwitter = new JButton("My Feed");
		
		
		btnISCTEIULTwitter = new JButton("ISCTE-IUL");
		
		btnSearchTwitter = new JButton("Search");
		
		
		textSearchFieldTwitter = new JTextField();
		textSearchFieldTwitter.setColumns(10);
		
		listTwitter = new JList<>(model);
		listTwitter.setBorder(new CompoundBorder());
		listTwitter.setFont(new Font("Consolas", Font.PLAIN, 13));
		
		textAreaTweet = new JTextArea();
		textAreaTweet.setEditable(false);
		textAreaTweet.setFont(new Font("Consolas", Font.PLAIN, 13));
		textAreaTweet.setWrapStyleWord(true);
		textAreaTweet.setLineWrap(true);
		
		btnRetweet = new JButton("Retweet");
		
		
		
		GroupLayout gl_panelTwitter = new GroupLayout(panelTwitter);
		gl_panelTwitter.setHorizontalGroup(
			gl_panelTwitter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTwitter.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelTwitter.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnRetweet)
						.addGroup(gl_panelTwitter.createSequentialGroup()
							.addGroup(gl_panelTwitter.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnMyFeedTwitter, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnISCTEIULTwitter, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panelTwitter.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textAreaTweet)
								.addComponent(listTwitter, GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textSearchFieldTwitter, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSearchTwitter)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panelTwitter.setVerticalGroup(
			gl_panelTwitter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTwitter.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panelTwitter.createParallelGroup(Alignment.BASELINE)
						.addGroup(gl_panelTwitter.createSequentialGroup()
							.addComponent(btnMyFeedTwitter)
							.addGap(5)
							.addComponent(btnISCTEIULTwitter))
						.addComponent(listTwitter, GroupLayout.PREFERRED_SIZE, 343, GroupLayout.PREFERRED_SIZE)
						.addComponent(textSearchFieldTwitter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearchTwitter))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textAreaTweet, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnRetweet)
					.addGap(15))
		);
		panelTwitter.setLayout(gl_panelTwitter);
		
		JPanel panelEmail = new JPanel();
		tabbedPane.addTab("E-mail", null, panelEmail, null);
		GroupLayout gl_panelEmail = new GroupLayout(panelEmail);
		gl_panelEmail.setHorizontalGroup(
			gl_panelEmail.createParallelGroup(Alignment.LEADING)
				.addGap(0, 733, Short.MAX_VALUE)
		);
		gl_panelEmail.setVerticalGroup(
			gl_panelEmail.createParallelGroup(Alignment.LEADING)
				.addGap(0, 398, Short.MAX_VALUE)
		);
		panelEmail.setLayout(gl_panelEmail);
		
		JPanel panelHelp = new JPanel();
		tabbedPane.addTab("Help", null, panelHelp, null);
		
		JLabel lblNewLabel = new JLabel("Informa\u00E7\u00F5es:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblNewLabel_2 = new JLabel("Frederico Pais\r\n");
		
		JLabel lblNewLabel_3 = new JLabel("Filipe Agostinho");
		
		JLabel lblNewLabel_4 = new JLabel("Rita Mendes Fonseca");
		
		JLabel lblNewLabel_5 = new JLabel("Sara Jessica");
		
		JLabel lblNewLabel_1 = new JLabel("Grupo 26");
		
		GroupLayout gl_panelHelp = new GroupLayout(panelHelp);
		gl_panelHelp.setHorizontalGroup(
			gl_panelHelp.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHelp.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelHelp.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelHelp.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_1))
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_4)
						.addComponent(lblNewLabel_5))
					.addContainerGap(587, Short.MAX_VALUE))
		);
		gl_panelHelp.setVerticalGroup(
			gl_panelHelp.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHelp.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelHelp.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_4)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_5)
					.addContainerGap(291, Short.MAX_VALUE))
		);
		panelHelp.setLayout(gl_panelHelp);
		contentPane.setLayout(gl_contentPane);
	}
}
