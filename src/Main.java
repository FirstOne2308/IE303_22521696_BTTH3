package src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main extends JFrame {
    private JLabel imgLabel, nameLabel, priceLabel, brandLabel, descLabel;
    private JPanel detailPanel, gridPanel;
    private Product selectedProduct;

    public Main() {
        setTitle("Adidas Shoe Store");
        setSize(1280, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        detailPanel = new JPanel();
        detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
        detailPanel.setPreferredSize(new Dimension(500, 700));
        detailPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        detailPanel.setBackground(Color.WHITE);

        imgLabel = new JLabel();
        nameLabel = new JLabel();
        priceLabel = new JLabel();
        brandLabel = new JLabel();
        descLabel = new JLabel();

        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        priceLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        brandLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        descLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));

        detailPanel.add(imgLabel);
        detailPanel.add(Box.createVerticalStrut(20));
        detailPanel.add(nameLabel);
        detailPanel.add(priceLabel);
        detailPanel.add(brandLabel);
        detailPanel.add(descLabel);

        gridPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        gridPanel.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(detailPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);

        renderProducts(getProductList());
        setVisible(true);
    }

    private void renderProducts(ArrayList<Product> products) {
        gridPanel.removeAll();
        for (Product p : products) {
            ProductCard card = new ProductCard(p, () -> {
                selectedProduct = p;
                updateDetail(p);
                renderProducts(products);
            }, p == selectedProduct);
            gridPanel.add(card);
        }
        if (selectedProduct == null && !products.isEmpty()) {
            selectedProduct = products.get(0);
            updateDetail(selectedProduct);
            renderProducts(products);
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    private void updateDetail(Product product) {
        ImageIcon original = new ImageIcon(product.imagePath);
        Image scaled = original.getImage().getScaledInstance(380, 380, Image.SCALE_SMOOTH);
        imgLabel.setIcon(new ImageIcon(scaled));
        nameLabel.setText(product.name);
        priceLabel.setText("$" + product.price);
        brandLabel.setText(product.brand);
        descLabel.setText("<html><br><i>" + product.description + "</i></html>");
    }

    private ArrayList<Product> getProductList() {
        ArrayList<Product> list = new ArrayList<>();
        list.add(new Product("4DFWD PULSE SHOES", "Adidas", 160.0, "Excluded from discounts.", "assets/img1.png"));
        list.add(new Product("FORUM MID SHOES", "Adidas", 100.0, "Mid-top classic.", "assets/img2.png"));
        list.add(new Product("SUPERNOVA SHOES", "Adidas", 150.0, "Running performance.", "assets/img3.png"));
        list.add(new Product("NMD City Stock 2", "Adidas", 160.0, "Urban style stock.", "assets/img4.png"));
        list.add(new Product("ULTRABOOST", "Adidas", 120.0, "Responsive cushioning.", "assets/img5.png"));
        list.add(new Product("4DFWD PULSE (Red)", "Adidas", 160.0, "Exclusive red edition.", "assets/img6.png"));
        return list;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
