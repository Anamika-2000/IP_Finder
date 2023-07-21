import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

public class IPFinder extends JFrame implements ActionListener {
    private JLabel urlLabel;
    private JTextField urlTextField;
    private JButton findIpButton;

    IPFinder() {
        super("IP Finder Tool");

        // Labels to instruct the user
        urlLabel = new JLabel("Enter URL:");
        JLabel instructionLabel = new JLabel("Enter a URL and click 'Find IP' to get the IP address.");

        urlTextField = new JTextField(30);
        findIpButton = new JButton("Find IP");
        findIpButton.addActionListener(this);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputPanel.add(urlLabel);
        inputPanel.add(urlTextField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        // Improve button design
        findIpButton.setPreferredSize(new Dimension(100, 30));
        findIpButton.setBackground(new Color(0, 153, 204)); // Set a custom color for the button
        findIpButton.setForeground(Color.WHITE); // Set the text color to white
        buttonPanel.add(findIpButton);

        mainPanel.add(instructionLabel, BorderLayout.NORTH); // Add instruction label at the top
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String url = urlTextField.getText();
        if (url.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a URL.");
            return;
        }
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }
        try {
            InetAddress ia = InetAddress.getByName(new URL(url).getHost());
            String ip = ia.getHostAddress();

            // Format the IP address
            String formattedIP = formatIPAddress(ip);

            JOptionPane.showMessageDialog(this, "IP Address: " + formattedIP);
            urlTextField.setText(""); // Clear the URL text field
        } catch (MalformedURLException e1) {
            JOptionPane.showMessageDialog(this, "Invalid URL format.");
        } catch (UnknownHostException e2) {
            JOptionPane.showMessageDialog(this, "Failed to resolve the host enter valid URL: " + e2.getMessage());
        }
    }

    // Method to format IP address (e.g., 192.168.0.1)
    private String formatIPAddress(String ip) {
        String[] parts = ip.split("\\.");
        return String.format("%s.%s.%s.%s", parts[0], parts[1], parts[2], parts[3]);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new IPFinder();
            }
        });
    }
}
