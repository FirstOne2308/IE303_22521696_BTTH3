package src;

import javax.swing.*;
import java.awt.*;

public class ProductCard extends JPanel {
    private static final Color SELECTED_COLOR = new Color(0x3B82F6); // xanh viền khi chọn
    private static final Color NORMAL_BORDER = new Color(220, 220, 220); // viền thường

    public ProductCard(Product product, Runnable onClick, boolean isSelected) {
        setLayout(new BorderLayout(5, 5));
        setPreferredSize(new Dimension(200, 300));
        setBorder(BorderFactory.createLineBorder(isSelected ? SELECTED_COLOR : NORMAL_BORDER, 2));
        setBackground(Color.WHITE);

        // Tên sản phẩm
        JLabel name = new JLabel(product.name, JLabel.CENTER);
        name.setFont(new Font("SansSerif", Font.BOLD, 12));

        // Mô tả dưới tên
        JLabel desc = new JLabel( product.description );
        desc.setFont(new Font("SansSerif", Font.PLAIN, 11));
        desc.setForeground(Color.GRAY);

        // Panel chứa name + description (trên cùng)
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(Color.WHITE);
        topPanel.add(name);
        topPanel.add(desc);

        // Ảnh sản phẩm (ở giữa)
        ImageIcon icon = new ImageIcon(product.imagePath);
        Image scaled = icon.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        JLabel img = new JLabel(new ImageIcon(scaled), JLabel.CENTER);

        // Brand + Price cùng dòng
        JLabel brand = new JLabel(product.brand);
        JLabel price = new JLabel("$" + product.price);
        brand.setFont(new Font("SansSerif", Font.PLAIN, 11));
        price.setFont(new Font("SansSerif", Font.BOLD, 12));

        JPanel brandPricePanel = new JPanel(new BorderLayout());
        brandPricePanel.setBackground(Color.WHITE);
        brandPricePanel.add(brand, BorderLayout.WEST);
        brandPricePanel.add(price, BorderLayout.EAST);

        // Panel dưới cùng
        JPanel bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
        bottom.setBackground(Color.WHITE);
        bottom.add(Box.createVerticalStrut(5));
        bottom.add(brandPricePanel);

        // Gộp vào card
        add(topPanel, BorderLayout.NORTH);
        add(img, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onClick.run();
            }
        });
    }
}
