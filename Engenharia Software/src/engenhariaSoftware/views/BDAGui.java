package engenhariaSoftware.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.Post;
import com.restfb.types.User;

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
import engenhariaSoftware.common.PostFacebook;
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
import javax.swing.JSplitPane;
import java.awt.GridLayout;

/**
 * @author Frederico
 * @author Sara
 * @author Rita
 * @author Filipe
 * @since Octuber,2018
 * @version 2.0
 * 
 *  The BDAGui class access to academic information made available through various channels,
 *  such as Email, Facebook and Twitter
 *      
 *
 */

public class BDAGui extends JFrame {
	
	private JPanel contentPane;
	
	/*
	 * Twitter
	 */
	private JButton btnMyFeedTwitter;
	private JButton btnISCTEIULTwitter;
	private JTextField textSearchFieldTwitter;
	private JButton btnSearchTwitter;
	private DefaultListModel<String> modelTwitter = new DefaultListModel<>();
	private JList listTwitter;
	private ArrayList<Tweet> listaTweets = new ArrayList<>();
	private JTextArea textAreaTweet;
	private JButton btnRetweet;
	private JButton btnAEISCTETwitter;
	private JButton btnBiblioISCTETwitter;
	private JScrollPane scrollPaneTweet;
	
	/*
	 * Facebook
	 */
	private DefaultListModel<String> modelFacebook = new DefaultListModel<>();
	private JButton btnMyFeedFacebook;
	private JTextArea textAreaPost;
	private JList listFacebook;
	private ArrayList<PostFacebook> listaPosts = new ArrayList<>();

	/**
	 * Main to launch the application.
	 * @param args gives the argument to use
	 * 
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
	 * BDAGui constructor, 
	 * Create the frame.
	 * 
	*/
	public BDAGui() {
		setResizable(false);
		initComponents();
		try {
			createEventsTwitter();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createEventsFacebook();
		createEventsMail();
		
	}
	
	/**
	 * createEventsMail, 
	 * This method contains all of the code for creating events for the Outlook app.
	 */
	
	private void createEventsMail() {
		
	}
	
	/**
	 * createEventsFacebook
	 * This method contains all of the code for creating events for the Facebook app.
	 */
	
	private void createEventsFacebook() {
		String accessToken = "EAANNMhF9bF0BAGNTpUzZCXlyZCZBaRQZCaFEZADMqNHiXiSO6q8soNO6I26coIVinxTefRKLZCwclpjT3Hfv5tvffpemHjGSVBqakzifaihYoKZCBp2nXK4Fi9lL17ZBuX6owxlkRvbqjpSdxMbAGDvyaKYVd2meUdmbUBB2Sw3xIgZDZD";

		FacebookClient facebookClient = new DefaultFacebookClient(accessToken, Version.VERSION_2_10);
		
		User me = facebookClient.fetchObject("me", User.class);

		Connection<Post> result = facebookClient.fetchConnection("me/feed", Post.class);
		
		btnMyFeedFacebook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modelFacebook.clear();
				for(List<Post> page: result) {
					
					for(Post aPost : page) {
						String user = me.getName();
						String text = aPost.getMessage();
						String id = aPost.getId();
						Date createdAt = aPost.getCreatedTime();
						PostFacebook postfacebook = new PostFacebook(user, text, id, createdAt);
						modelFacebook.addElement(postfacebook.postHeader());
						listaPosts.add(postfacebook);
//						modelFacebook.addElement(""+aPost.getCreatedTime());
//						System.out.println(aPost.getMessage());
//						System.out.println("fb.com/" + aPost.getId());
						
				}
				
				}
			
			}
		});
		
		listFacebook.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				textAreaPost.setText("");
				String selectedValue = (String) listFacebook.getSelectedValue();
				System.out.println();
				for(PostFacebook p: listaPosts) {
					if(selectedValue != null && selectedValue.equals(p.postHeader())) {
						textAreaPost.append(p.getUserName() + " | " + p.getCreatedAt());
						textAreaPost.append("\n\n");
						textAreaPost.append(p.getText());
					}
				}
//				for(List<Post> page: result) {
//					for(Post aPost : page) {
//						if(selectedValue != null && selectedValue.equals(""+aPost.getCreatedTime())) {
//							textAreaPost.append(aPost.getName() + " | " + aPost.getCreatedTime());
//							textAreaPost.append("\n\n");
//							textAreaPost.append(aPost.getMessage());
//						}
//
//					}
//				}

			}
		});

		
	}

	/**
	 * createEventsTwitter,
	 * This method contains all of the code for creating events for the Twitter app
	 * @throws TwitterException 
	 * @see TwitterException
	 *
	 */
	
	private void createEventsTwitter() throws TwitterException {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		
		cb.setDebugEnabled(true).setOAuthConsumerKey("x2pTILrYxrf7tE1r0dvuv9jWG").setOAuthConsumerSecret("I2elQ8AGRjfRme2f8PWG4OHwm0bCzOrYxvkzcmB00jJ8iXrvam").setOAuthAccessToken("1051429457913896962-KsecOb4dSYC2tY9YSfZKVnUgZilxyl").setOAuthAccessTokenSecret("4o63MCLz1VIMU6asHCa72cVppUdMcwsB40Y58u1pIk02p");
		
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter4j.Twitter twitter = tf.getInstance();
		
		//get username, status
		List<Status> statusHome = twitter.getHomeTimeline();
		List<Status> statusIscte = twitter.getUserTimeline("iscteiul");
		List<Status> statusAEIscte = twitter.getUserTimeline("aeiscte");
		List<Status> statusBiblioIscte = twitter.getUserTimeline("bibliotecaiscte");
		
		btnMyFeedTwitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modelTwitter.clear();
				listaTweets.clear();
				for(Status st: statusHome) {
					String user = st.getUser().getName();
					String text = st.getText();
					long id = st.getId();
					Date createdAt = st.getCreatedAt();
					Tweet tweet = new Tweet(user, text, id, createdAt);
					modelTwitter.addElement(tweet.tweetHeader());
					listaTweets.add(tweet);
					
				}	
			
			}
		});
		
		btnISCTEIULTwitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelTwitter.clear();
				listaTweets.clear();
				for(Status st: statusIscte) {
					String user = st.getUser().getName();
					String text = st.getText();
					long id = st.getId();
					Date createdAt = st.getCreatedAt();
					Tweet tweet = new Tweet(user, text, id, createdAt);
					modelTwitter.addElement(tweet.tweetHeader());
					listaTweets.add(tweet);
				}	
			}
		});

		btnAEISCTETwitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modelTwitter.clear();
				listaTweets.clear();
				for(Status st: statusAEIscte) {
					String user = st.getUser().getName();
					String text = st.getText();
					long id = st.getId();
					Date createdAt = st.getCreatedAt();
					Tweet tweet = new Tweet(user, text, id, createdAt);
					modelTwitter.addElement(tweet.tweetHeader());
					listaTweets.add(tweet);
				}	
			}
		});
		btnBiblioISCTETwitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modelTwitter.clear();
				listaTweets.clear();
				for(Status st: statusBiblioIscte) {
					String user = st.getUser().getName();
					String text = st.getText();
					long id = st.getId();
					Date createdAt = st.getCreatedAt();
					Tweet tweet = new Tweet(user, text, id, createdAt);
					modelTwitter.addElement(tweet.tweetHeader());
					listaTweets.add(tweet);
				}
			}
		});
		
		btnSearchTwitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelTwitter.clear();
				listaTweets.clear();
				String searchTwitter = textSearchFieldTwitter.getText();
				Query query = new Query(searchTwitter);
				try {
					QueryResult result = twitter.search(query);
					List<Status> tweets = result.getTweets();
					for(Status st: tweets) {
						String user = st.getUser().getName();
						String text = st.getText();
						long id = st.getId();
						Date createdAt = st.getCreatedAt();
						Tweet tweet = new Tweet(user, text, id, createdAt);
						modelTwitter.addElement(tweet.tweetHeader());
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
					if(selectedValue != null && selectedValue.equals(t.tweetHeader())) {
						textAreaTweet.append(t.getUserName() + " | " + t.getCreatedAt());
						textAreaTweet.append("\n\n");
						textAreaTweet.append(t.getText());
					}
				}
			}
		});
		
	}

	/**
	 * initComponents,
	 * This method contains all of the code for creating events
	 * and initializing components of the GUI.
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
		panelHome.setToolTipText("P\u00E1gina inicial da aplica\u00E7\u00E3o.");
		panelHome.setBackground(Color.WHITE);
		tabbedPane.addTab("Home", null, panelHome, null);
		
		JLabel lblBDAHome = new JLabel("Bom Dia Academia!");
		lblBDAHome.setFont(new Font("Verdana", Font.BOLD, 14));
		
		JTextArea txtHomeAnounces = new JTextArea();
		txtHomeAnounces.setFont(new Font("Verdana", Font.PLAIN, 13));
		txtHomeAnounces.setBackground(Color.WHITE);
		txtHomeAnounces.setLineWrap(true);
		txtHomeAnounces.setWrapStyleWord(true);
		txtHomeAnounces.setText("\r\nBom dia Academia \u00E9 uma aplica\u00E7\u00E3o agregadora de conte\u00FAdo acad\u00E9mico das aplica\u00E7\u00F5es Facebook, Twitter e Outlook desenvolvida com recurso \u00E0s API's de cada aplica\u00E7\u00E3o de forma a ir buscar dados as essas plataformas.\r\n\r\nFuncionalidades v1: Twitter\r\n\r\n\u00DAltima atualiza\u00E7\u00E3o: 20/10/2018");
		txtHomeAnounces.setEditable(false);
		
		JLabel lblOutlookHome = new JLabel("");
		lblOutlookHome.setIcon(new ImageIcon(BDAGui.class.getResource("/engenhariaSoftware/resources/if_Outlook_128x128.png")));
		
		JLabel lblFacebookHome = new JLabel("");
		lblFacebookHome.setIcon(new ImageIcon(BDAGui.class.getResource("/engenhariaSoftware/resources/if_Facebook_128x128.png")));
		
		JLabel lblTwitterHome = new JLabel("");
		lblTwitterHome.setIcon(new ImageIcon(BDAGui.class.getResource("/engenhariaSoftware/resources/if_twitter_128x128.png")));
		GroupLayout gl_panelHome = new GroupLayout(panelHome);
		gl_panelHome.setHorizontalGroup(
			gl_panelHome.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHome.createSequentialGroup()
					.addGap(275)
					.addComponent(lblOutlookHome)
					.addGap(18)
					.addComponent(lblFacebookHome)
					.addGap(18)
					.addComponent(lblTwitterHome)
					.addGap(283))
				.addGroup(gl_panelHome.createSequentialGroup()
					.addComponent(txtHomeAnounces, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panelHome.createSequentialGroup()
					.addComponent(lblBDAHome)
					.addContainerGap())
		);
		gl_panelHome.setVerticalGroup(
			gl_panelHome.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHome.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBDAHome)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtHomeAnounces, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
					.addGap(172)
					.addGroup(gl_panelHome.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFacebookHome)
						.addComponent(lblOutlookHome)
						.addComponent(lblTwitterHome))
					.addContainerGap(72, Short.MAX_VALUE))
		);
		panelHome.setLayout(gl_panelHome);
		
		JPanel panelFacebook = new JPanel();
		panelFacebook.setBackground(new Color(51, 102, 204));
		tabbedPane.addTab("Facebook", null, panelFacebook, null);
		
		btnMyFeedFacebook = new JButton("My Feed");
		
		JScrollPane scrollPaneFacebookPostsList = new JScrollPane();
		
		JScrollPane scrollPanePost = new JScrollPane();
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(BDAGui.class.getResource("/engenhariaSoftware/resources/FacebookLogo_128x128.png")));
		GroupLayout gl_panelFacebook = new GroupLayout(panelFacebook);
		gl_panelFacebook.setHorizontalGroup(
			gl_panelFacebook.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelFacebook.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelFacebook.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelFacebook.createSequentialGroup()
							.addComponent(btnMyFeedFacebook, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
							.addGap(18))
						.addGroup(gl_panelFacebook.createSequentialGroup()
							.addComponent(lblNewLabel_5)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(gl_panelFacebook.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPanePost, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
						.addComponent(scrollPaneFacebookPostsList, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE))
					.addGap(290))
		);
		gl_panelFacebook.setVerticalGroup(
			gl_panelFacebook.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelFacebook.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelFacebook.createParallelGroup(Alignment.LEADING)
						.addComponent(btnMyFeedFacebook)
						.addComponent(scrollPaneFacebookPostsList, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelFacebook.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(scrollPanePost)
						.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(38))
		);
		
		textAreaPost = new JTextArea();
		textAreaPost.setEditable(false);
		textAreaPost.setFont(new Font("Verdana", Font.PLAIN, 13));
		textAreaPost.setWrapStyleWord(true);
		textAreaPost.setLineWrap(true);
		scrollPanePost.setViewportView(textAreaPost);
		
		listFacebook = new JList<>(modelFacebook);
		listFacebook.setFont(new Font("Verdana", Font.PLAIN, 13));
		listFacebook.setBorder(new CompoundBorder());
		scrollPaneFacebookPostsList.setViewportView(listFacebook);
		panelFacebook.setLayout(gl_panelFacebook);
		
		JPanel panelTwitter = new JPanel();
		panelTwitter.setBackground(new Color(176, 224, 230));
		tabbedPane.addTab("Twitter", null, panelTwitter, null);
		
		btnMyFeedTwitter = new JButton("My Feed");
		
		
		btnISCTEIULTwitter = new JButton("ISCTE-IUL");
		
		btnSearchTwitter = new JButton("Search");
		
		
		textSearchFieldTwitter = new JTextField();
		textSearchFieldTwitter.setColumns(10);
		
		btnRetweet = new JButton("Retweet");
		
		btnAEISCTETwitter = new JButton("AEISCTE");
		
		JLabel lblTwitterLogo = new JLabel("");
		lblTwitterLogo.setIcon(new ImageIcon(BDAGui.class.getResource("/engenhariaSoftware/resources/TwitterLogo_128x128.png")));
		
		btnBiblioISCTETwitter = new JButton("Biblioteca ISCTE");
		
		scrollPaneTweet = new JScrollPane();
		
		JScrollPane scrollPaneTweetsList = new JScrollPane();
		
		
		
		
		
		GroupLayout gl_panelTwitter = new GroupLayout(panelTwitter);
		gl_panelTwitter.setHorizontalGroup(
			gl_panelTwitter.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelTwitter.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelTwitter.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnISCTEIULTwitter, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnMyFeedTwitter, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAEISCTETwitter, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBiblioISCTETwitter, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTwitterLogo))
					.addGap(18)
					.addGroup(gl_panelTwitter.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneTweetsList, GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
						.addGroup(gl_panelTwitter.createSequentialGroup()
							.addGap(438)
							.addComponent(btnRetweet)
							.addGap(0, 0, Short.MAX_VALUE))
						.addComponent(scrollPaneTweet, GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(textSearchFieldTwitter, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSearchTwitter)
					.addGap(14))
		);
		gl_panelTwitter.setVerticalGroup(
			gl_panelTwitter.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelTwitter.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelTwitter.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_panelTwitter.createSequentialGroup()
							.addGap(419)
							.addComponent(lblTwitterLogo))
						.addGroup(Alignment.LEADING, gl_panelTwitter.createSequentialGroup()
							.addGroup(gl_panelTwitter.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelTwitter.createSequentialGroup()
									.addComponent(btnMyFeedTwitter)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnISCTEIULTwitter)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAEISCTETwitter)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnBiblioISCTETwitter))
								.addGroup(gl_panelTwitter.createParallelGroup(Alignment.BASELINE)
									.addComponent(scrollPaneTweetsList, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
									.addComponent(textSearchFieldTwitter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnSearchTwitter)))
							.addGap(11)
							.addComponent(scrollPaneTweet, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnRetweet)))
					.addContainerGap())
		);
		
		listTwitter = new JList<>(modelTwitter);
		scrollPaneTweetsList.setViewportView(listTwitter);
		listTwitter.setBorder(new CompoundBorder());
		listTwitter.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		textAreaTweet = new JTextArea();
		scrollPaneTweet.setViewportView(textAreaTweet);
		textAreaTweet.setEditable(false);
		textAreaTweet.setFont(new Font("Verdana", Font.PLAIN, 13));
		textAreaTweet.setWrapStyleWord(true);
		textAreaTweet.setLineWrap(true);
		panelTwitter.setLayout(gl_panelTwitter);
		
		JPanel panelEmail = new JPanel();
		tabbedPane.addTab("Outlook", null, panelEmail, null);
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
		panelHelp.setBackground(Color.WHITE);
		tabbedPane.addTab("Help", null, panelHelp, null);
		
		JLabel lblInstructions = new JLabel("Instru\u00E7\u00F5es");
		lblInstructions.setFont(new Font("Verdana", Font.BOLD, 14));
		
		JScrollPane scrollPane = new JScrollPane();
		
		GroupLayout gl_panelHelp = new GroupLayout(panelHelp);
		gl_panelHelp.setHorizontalGroup(
			gl_panelHelp.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHelp.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelHelp.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 958, Short.MAX_VALUE)
						.addComponent(lblInstructions))
					.addContainerGap())
		);
		gl_panelHelp.setVerticalGroup(
			gl_panelHelp.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHelp.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblInstructions)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JTextArea txtrAquiPodeAprender = new JTextArea();
		txtrAquiPodeAprender.setWrapStyleWord(true);
		txtrAquiPodeAprender.setLineWrap(true);
		txtrAquiPodeAprender.setText("Aqui pode aprender como utilizar a aplica\u00E7\u00E3o BDA e saber sobre todas as suas funcionalidades.\r\n\r\nHome:\r\n   - Introdu\u00E7\u00E3o \u00E0 aplica\u00E7\u00E3o;\r\n   - Vers\u00E3o e funcionalidades dispon\u00EDveis;\r\n   - Data da \u00FAltima atualiza\u00E7\u00E3o.\r\n\r\nFacebook:\r\n   - AINDA N\u00C3O EST\u00C1 DISPON\u00CDVEL.\r\n\r\nTwitter:\r\n   - \"My Feed\": Mostra os tweets mais recentes do seu feed de not\u00EDcias;\r\n   - \"ISCTE-IUL\": Mostra os tweets mais recentes da conta do ISCTE-IUL;\r\n   - \"AEISCTE\": Mostra os tweets mais recentes da conta da Associa\u00E7\u00E3o de Estudante do ISCTE-IUL;\r\n   - \"Biblioteca ISCTE\": Mostra os tweets mais recentes da conta da Biblioteca do ISCTE-IUL.\r\n   - \"Search\": Permite pesquisar por tweets que contenham a palavra introduzia pelo utilizador na barra de pesquisa;\r\n   - \"Retweet\": Permite retweetar. Para isto ser poss\u00EDvel \u00E9 necess\u00E1rio que tenha um tweet selecionado primeiro.\r\n\r\nOutlook:\r\n   - AINDA N\u00C3O EST\u00C1 DISPON\u00CDVEL.\r\n\r\nAbout Us:\r\n   - Informa\u00E7\u00F5es sobre os desenvolvedores desta aplica\u00E7\u00E3o.");
		txtrAquiPodeAprender.setFont(new Font("Verdana", Font.PLAIN, 13));
		scrollPane.setViewportView(txtrAquiPodeAprender);
		panelHelp.setLayout(gl_panelHelp);
		
		JPanel panelAbout = new JPanel();
		panelAbout.setBackground(Color.WHITE);
		tabbedPane.addTab("About Us", null, panelAbout, null);
		
		JLabel lblAboutTitle = new JLabel("Desenvolvedores da Aplica\u00E7\u00E3o");
		lblAboutTitle.setFont(new Font("Verdana", Font.BOLD, 14));
		
		JLabel lblRita = new JLabel("");
		lblRita.setIcon(new ImageIcon(BDAGui.class.getResource("/engenhariaSoftware/resources/Rita.jpg")));
		
		JLabel lblSara = new JLabel("");
		lblSara.setIcon(new ImageIcon(BDAGui.class.getResource("/engenhariaSoftware/resources/Sara.jpg")));
		
		JLabel lblFilipe = new JLabel("");
		lblFilipe.setIcon(new ImageIcon(BDAGui.class.getResource("/engenhariaSoftware/resources/Filipe.jpg")));
		
		JLabel lblFrederico = new JLabel("");
		lblFrederico.setIcon(new ImageIcon(BDAGui.class.getResource("/engenhariaSoftware/resources/Frederico.jpg")));
		
		JLabel lblNewLabel = new JLabel("Rita Mendes Fonseca, N\u00BA 68676");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		
		JLabel lblNewLabel_1 = new JLabel("Sara J\u00E9ssica, N\u00BA 73501");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 12));
		
		JLabel lblNewLabel_2 = new JLabel("Filipe Agostinho, N\u00BA 78011");
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 12));
		
		JLabel lblNewLabel_3 = new JLabel("Frederico Pais, N\u00BA 77707");
		lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 12));
		
		JLabel lblNewLabel_4 = new JLabel("Engenharia de Software I - Grupo 26");
		lblNewLabel_4.setFont(new Font("Verdana", Font.BOLD, 12));
		
		JLabel lblISCTE = new JLabel("");
		lblISCTE.setIcon(new ImageIcon(BDAGui.class.getResource("/engenhariaSoftware/resources/ISCTE.jpg")));
		GroupLayout gl_panelAbout = new GroupLayout(panelAbout);
		gl_panelAbout.setHorizontalGroup(
			gl_panelAbout.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelAbout.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelAbout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAboutTitle)
						.addGroup(gl_panelAbout.createSequentialGroup()
							.addComponent(lblRita)
							.addGroup(gl_panelAbout.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelAbout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblNewLabel))
								.addGroup(Alignment.TRAILING, gl_panelAbout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 702, Short.MAX_VALUE)
									.addComponent(lblISCTE))))
						.addGroup(gl_panelAbout.createSequentialGroup()
							.addComponent(lblSara)
							.addGap(18)
							.addComponent(lblNewLabel_1))
						.addGroup(gl_panelAbout.createSequentialGroup()
							.addComponent(lblFilipe)
							.addGap(18)
							.addComponent(lblNewLabel_2))
						.addGroup(gl_panelAbout.createSequentialGroup()
							.addComponent(lblFrederico)
							.addGap(18)
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.RELATED, 616, Short.MAX_VALUE)
							.addComponent(lblNewLabel_4)))
					.addContainerGap())
		);
		gl_panelAbout.setVerticalGroup(
			gl_panelAbout.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelAbout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAboutTitle)
					.addGap(18)
					.addGroup(gl_panelAbout.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelAbout.createSequentialGroup()
							.addGroup(gl_panelAbout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblRita)
								.addComponent(lblNewLabel))
							.addGap(18)
							.addGroup(gl_panelAbout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblSara)
								.addComponent(lblNewLabel_1))
							.addGap(18)
							.addGroup(gl_panelAbout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblFilipe)
								.addComponent(lblNewLabel_2)))
						.addComponent(lblISCTE))
					.addGap(18)
					.addGroup(gl_panelAbout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblFrederico)
						.addGroup(gl_panelAbout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_3)
							.addComponent(lblNewLabel_4)))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		panelAbout.setLayout(gl_panelAbout);
		contentPane.setLayout(gl_contentPane);
	}
}
