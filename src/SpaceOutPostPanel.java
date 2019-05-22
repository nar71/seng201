import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;

public class SpaceOutPostPanel extends JPanel {

    /**
     * A string representation of the inventory panel, used for Card Layout.
     */
    private static final String INVENTORY_PANEL_STRING = "INVENTORY_PANEL";

    /**
     * A string representation of the medical supplies panel, used for Card Layout.
     */
    private static final String MEDICAL_SUPPLIES_PANEL_STRING = "MEDICAL_SUPPLIES_PANEL";

    /**
     * A string representation of the food panel, used for Card Layout.
     */
    private static final String FOODS_PANEL_STRING = "FOODS_PANEL";

    /**
     * The space out post which holds all the medical supplies and foods
     */
    private SpaceOutPost spaceOutPost;

    /**
     * A card layout used by content.
     */
    private CardLayout cardLayout;

    /*
     * The main content panel, which is used for every different card.
     */
    private JPanel content;

    /**
     * A panel component of content.
     */
    private JPanel inventoryPanel;

    /**
     * A panel component of content.
     */
    private JPanel medicalSuppliesPanel;

    /**
     * A panel component of content.
     */
    private JPanel foodsPanel;

    /**
     * A label component of side bar.
     */
    private JLabel lblCoins;

    /**
     * A button component of side bar.
     */
    private JButton inventoryBtn;

    /**
     * A button component of side bar.
     */
    private JButton medicalSuppliesBtn;

    /**
     * A button component of side bar.
     */
    private JButton foodBtn;

    /**
     * SpaceOutPostPanel constructor
     * @param spaceOutPost The space out post
     */
	SpaceOutPostPanel(SpaceOutPost spaceOutPost) {
		super();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(new EmptyBorder(30,0,0,0));

        this.spaceOutPost = spaceOutPost;

		this.content = new JPanel();
        this.cardLayout = new CardLayout();
        content.setLayout(cardLayout);

		addSideBar();
		add(content);
        addInventoryPanel();
        refreshInventoryPanel();
        addMedicalSuppliesPanel();
        addFoodsPanel();
	
        cardLayout.show(content, INVENTORY_PANEL_STRING);

        inventoryBtn.setEnabled(false);
        addActionListeners();
	}

    /**
     * Shows the inventory card - used on Home Screen by inventory and shop action handlers.
     */
    public void showInventoryCard() {
        cardLayout.show(content, INVENTORY_PANEL_STRING);
        refreshInventoryPanel();
    }

    /**
     * Refreshs the current coins with latest data.
     */
    public void refreshCoinLabel() {
        this.lblCoins.setText("Current coins: " + spaceOutPost.getCurrentMoney());
    }

    /**
     * Adds side bar panel with buttons.
     */
    public void addSideBar() {
        JPanel sideBar = new JPanel();
        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
        
        JPanel sideBarGrid = new JPanel(new GridLayout(4,1));
        sideBarGrid.setBorder(new EmptyBorder(0,10,0,0));
        sideBar.add(sideBarGrid);
        
        JPanel currentCoinsPanel = new JPanel();
        sideBarGrid.add(currentCoinsPanel);
        this.lblCoins = new JLabel("Current $" + spaceOutPost.getCurrentMoney());
        lblCoins.setFont(new Font("Tahoma", Font.PLAIN, 18));
        currentCoinsPanel.add(lblCoins);
        
        JPanel inventoryBtnPanel = new JPanel();
        sideBarGrid.add(inventoryBtnPanel);
        this.inventoryBtn = new JButton("Inventory");
        inventoryBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        inventoryBtnPanel.add(inventoryBtn);
        
        JPanel medicalSuppliesBtnPanel = new JPanel();
        sideBarGrid.add(medicalSuppliesBtnPanel);
        this.medicalSuppliesBtn = new JButton("Purchase Medical Supplies");
        medicalSuppliesBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        medicalSuppliesBtnPanel.add(medicalSuppliesBtn);
        
        JPanel foodBtnPanel = new JPanel();
        sideBarGrid.add(foodBtnPanel);
        this.foodBtn = new JButton("Purchase Food Items");
        foodBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        foodBtnPanel.add(foodBtn);

        sideBar.add(new JPanel());
        add(sideBar);
    }

    /**
     * Adds action listeners for sidebar buttons.
     */
    public void addActionListeners() {
    	foodBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg1) {
                refreshFoodsPanel();
                cardLayout.show(content, FOODS_PANEL_STRING);
                inventoryBtn.setEnabled(true);
                medicalSuppliesBtn.setEnabled(true);
                foodBtn.setEnabled(false);
            }
        });
        
        medicalSuppliesBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                refreshMedicalSuppliesPanel();
                cardLayout.show(content, MEDICAL_SUPPLIES_PANEL_STRING);
                inventoryBtn.setEnabled(true);
                foodBtn.setEnabled(true);
                medicalSuppliesBtn.setEnabled(false);
            }
        });
        
        inventoryBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg2) {
                refreshInventoryPanel();
                cardLayout.show(content, INVENTORY_PANEL_STRING);
                inventoryBtn.setEnabled(false);
                medicalSuppliesBtn.setEnabled(true);
                foodBtn.setEnabled(true);
            }
        });
    }

    /**
     * Adds inventory panel to content panel.
     */
    private void addInventoryPanel() {
        this.inventoryPanel = new JPanel();
        inventoryPanel.setLayout(null);
        content.add(inventoryPanel, INVENTORY_PANEL_STRING);
    }

    /**
     * Refreshs inventory panel.
     */
    private void refreshInventoryPanel() {
        inventoryPanel.removeAll();
        
        JLabel lblInventoryTitle = new JLabel("Inventory");
        lblInventoryTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblInventoryTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblInventoryTitle.setBounds(10, 0, 700, 50);
        inventoryPanel.add(lblInventoryTitle);
        
        JLabel lblFoodTitle = new JLabel("Food");
        lblFoodTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblFoodTitle.setBounds(100, 74, 181, 30);
        inventoryPanel.add(lblFoodTitle);
        
        int y = 124;
        for (Food food: spaceOutPost.getFoods()) {
            JLabel lblFoodImage = new JLabel("");
            lblFoodImage.setFont(new Font("Tahoma", Font.PLAIN, 16));
            lblFoodImage.setIcon(Funcs.getScaledIcon(Image.getFoodImagePath(food), 38, 38));   
            lblFoodImage.setBorder(new LineBorder(new Color(0, 0, 0)));
            lblFoodImage.setBounds(100, y, 38, 38);
            inventoryPanel.add(lblFoodImage);
            
            JLabel lblFoodType = new JLabel(food.getType() + "(" + food.getCount() + ")");
            lblFoodType.setFont(new Font("Tahoma", Font.PLAIN, 16));
            lblFoodType.setBounds(156, y, 187, 38);
            inventoryPanel.add(lblFoodType);
            
            y += 50;
        }
        
        JLabel lblMedicalSupplyTitle = new JLabel("Medical Supplies");
        lblMedicalSupplyTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblMedicalSupplyTitle.setBounds(439, 73, 250, 30);
        inventoryPanel.add(lblMedicalSupplyTitle);
        
        y = 124;
        for (MedicalSupply medicalSupply: spaceOutPost.getMedicalSupplies()) {
            JLabel lblMedicalSupplyImage = new JLabel("");
            lblMedicalSupplyImage.setFont(new Font("Tahoma", Font.PLAIN, 16));
            lblMedicalSupplyImage.setIcon(Funcs.getScaledIcon(Image.getMedicalSupplyImagePath(medicalSupply), 38, 38));   
            lblMedicalSupplyImage.setBorder(new LineBorder(new Color(0, 0, 0)));
            lblMedicalSupplyImage.setBounds(439, y, 38, 38);
            inventoryPanel.add(lblMedicalSupplyImage);
            
            JLabel lblMedicalSupplyType = new JLabel(medicalSupply.getType() + "(" + medicalSupply.getCount() + ")");
            lblMedicalSupplyType.setFont(new Font("Tahoma", Font.PLAIN, 16));
            lblMedicalSupplyType.setBounds(487, y, 187, 38);
            inventoryPanel.add(lblMedicalSupplyType);
            
            y += 50;
        }
    }

    /**
     * Adds medical supplies panel to content panel.
     */
    private void addMedicalSuppliesPanel() {
        this.medicalSuppliesPanel = new JPanel();
        medicalSuppliesPanel.setLayout(new GridLayout(2, 2, 0, 0));
        content.add(medicalSuppliesPanel, MEDICAL_SUPPLIES_PANEL_STRING);
    }

    /**
     * Refreshs medical supplies panel with latest data.
     */
    private void refreshMedicalSuppliesPanel() {
        medicalSuppliesPanel.removeAll();

        for (MedicalSupply medicalSupply: spaceOutPost.getMedicalSupplies()) {
            JPanel panel = new JPanel();
            panel.setLayout(null);
            
            JLabel lblMedicalSupply = new JLabel(medicalSupply.getType() + "(" + medicalSupply.getCount() + ") $" + medicalSupply.getCost());
            lblMedicalSupply.setBounds(80, 0, 250, 30);
            lblMedicalSupply.setFont(new Font("Tahoma", Font.PLAIN, 16));
            panel.add(lblMedicalSupply);

            JLabel lblMedicalSupplyImage = new JLabel("");
            lblMedicalSupplyImage.setIcon(Funcs.getScaledIcon(Image.getMedicalSupplyImagePath(medicalSupply), 150, 150));
            lblMedicalSupplyImage.setBorder(new LineBorder(new Color(0, 0, 0)));
            lblMedicalSupplyImage.setBounds(83, 33, 150, 150);
            panel.add(lblMedicalSupplyImage);
            
            JButton purchaseBtn = new JButton("Purchase");
            purchaseBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
            purchaseBtn.setBounds(83, 194, 150, 30);
            if (!spaceOutPost.canAffordItem(medicalSupply.getCost())) {
            	purchaseBtn.setEnabled(false);
            }
            
            JLabel lblHealth = new JLabel("Gives " + medicalSupply.getHealth() + " health");
            if (medicalSupply.isSpacePlagueCure()) {
                lblHealth = new JLabel("Cures space plague");
            }
            lblHealth.setFont(new Font("Tahoma", Font.PLAIN, 14));
            lblHealth.setBounds(83, 230, 250, 15);
            panel.add(lblHealth);

            purchaseBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg5) {
                    if (spaceOutPost.canAffordItem(medicalSupply.getCost())) {
                        spaceOutPost.purchaseMedicalSupply(medicalSupply);
                        lblCoins.setText("Current coins: " + spaceOutPost.getCurrentMoney());
                        refreshMedicalSuppliesPanel();
                    }
                }
            });
            panel.add(purchaseBtn);
            medicalSuppliesPanel.add(panel);
        }
    }

    /**
     * Adds foods panel to content panel.
     */
    private void addFoodsPanel() {
        this.foodsPanel = new JPanel();
        foodsPanel.setLayout(new GridLayout(2, 3, 0, 0));
        content.add(foodsPanel, FOODS_PANEL_STRING);
    }

    /**
     * Refreshs foods panel with latest data.
     */
    private void refreshFoodsPanel() {
        foodsPanel.removeAll();

        for (Food food: spaceOutPost.getFoods()) {
            JPanel panel = new JPanel();
            panel.setLayout(null);
            
            JLabel lblFood = new JLabel(food.getType() + "(" + food.getCount() + ") $" + food.getCost());
            lblFood.setBounds(80, 0, 180, 30);
            lblFood.setFont(new Font("Tahoma", Font.PLAIN, 16));
            panel.add(lblFood);

            JLabel lblFoodImage = new JLabel("");
            lblFoodImage.setIcon(Funcs.getScaledIcon(Image.getFoodImagePath(food), 150, 150));
            lblFoodImage.setBorder(new LineBorder(new Color(0, 0, 0)));
            lblFoodImage.setBounds(83, 33, 150, 150);
            panel.add(lblFoodImage);
            
            JButton purchaseBtn = new JButton("Purchase");
            purchaseBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
            purchaseBtn.setBounds(83, 194, 150, 30);

            if (!spaceOutPost.canAffordItem(food.getCost())) {
            	purchaseBtn.setEnabled(false);
            }
            
            JLabel lblHunger = new JLabel("Gives " + food.getHungerLevel() + " hunger");
            lblHunger.setFont(new Font("Tahoma", Font.PLAIN, 14));
            lblHunger.setBounds(83, 230, 250, 15);
            panel.add(lblHunger);

            purchaseBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg5) {
                    if (spaceOutPost.canAffordItem(food.getCost())) {
                        spaceOutPost.purchaseFood(food);
                        lblCoins.setText("Current coins: " + spaceOutPost.getCurrentMoney());
                        refreshFoodsPanel();
                    }
                }
            });
            panel.add(purchaseBtn);
            foodsPanel.add(panel);
        }
    }
}
