package pl.edu.agh.dronka.shop.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import pl.edu.agh.dronka.shop.controller.ShopController;
import pl.edu.agh.dronka.shop.model.BookItem;
import pl.edu.agh.dronka.shop.model.ElectronicItem;
import pl.edu.agh.dronka.shop.model.MusicItem;
import pl.edu.agh.dronka.shop.model.filter.ItemFilter;

public class PropertiesPanel extends JPanel {

    private static final long serialVersionUID = -2804446079853846996L;
    private ShopController shopController;

    private ItemFilter filter;

    public PropertiesPanel(ShopController shopController) {
        this.shopController = shopController;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

    }

    public void fillProperties() {
        removeAll();
        filter= new ItemFilter(shopController.getCurrentCategory());
        filter.getItemSpec().setCategory(shopController.getCurrentCategory());
        add(createPropertyCheckbox("Tanie bo polskie", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                filter.getItemSpec().setPolish(
                        ((JCheckBox) event.getSource()).isSelected());
                shopController.filterItems(filter);
            }
        }));

        add(createPropertyCheckbox("Używany", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                filter.getItemSpec().setSecondhand(
                        ((JCheckBox) event.getSource()).isSelected());
                shopController.filterItems(filter);
            }
        }));
        switch (filter.getItemSpec().getCategory()) {
            case ELECTRONICS:
                add(createPropertyCheckbox("Mobilny", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ((ElectronicItem) filter.getItemSpec()).setMobile(
                                ((JCheckBox) e.getSource()).isSelected());
                        shopController.filterItems(filter);
                    }
                }));
				add(createPropertyCheckbox("Gwarancja", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						((ElectronicItem) filter.getItemSpec()).setGuaranty(
								((JCheckBox) e.getSource()).isSelected());
						shopController.filterItems(filter);
					}
				}));
				break;
			case BOOKS:
				add(createPropertyCheckbox("Twarda oprawa", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						((BookItem) filter.getItemSpec()).setHardCover(
								((JCheckBox) e.getSource()).isSelected());
						shopController.filterItems(filter);
					}
				}));
                break;
            case MUSIC:
				add(createPropertyCheckbox("Wideo", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						((MusicItem) filter.getItemSpec()).setVideoConnected(
								((JCheckBox) e.getSource()).isSelected());
						shopController.filterItems(filter);
					}
				}));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + filter.getItemSpec().getCategory());
        }

    }

    private JCheckBox createPropertyCheckbox(String propertyName,
                                             ActionListener actionListener) {

        JCheckBox checkBox = new JCheckBox(propertyName);
        checkBox.setSelected(false);
        checkBox.addActionListener(actionListener);

        return checkBox;
    }

}
