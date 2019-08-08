package de.intexde.ffa.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuilder {
    private ItemStack item;


    public ItemBuilder(Material material){
        this(material, 1);
    }

    public ItemBuilder(ItemStack item){
        this.item = item;
    }

    public ItemBuilder(Material material, int amount){
        item = new ItemStack(material, amount);
    }

    public ItemBuilder(Material material, int amount, byte durability){
        item = new ItemStack(material, amount, durability);
    }

    public ItemBuilder setDurability(short durability){
        item.setDurability(durability);
        return this;
    }

    public ItemBuilder setDisplayName(String name){
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder addUnsafeEnchantment(Enchantment enchantment, int level){
        item.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemBuilder removeEnchantment(Enchantment enchantment){
        item.removeEnchantment(enchantment);
        return this;
    }

    public ItemBuilder setSkullOwner(String owner){
        try{
            SkullMeta meta = (SkullMeta)item.getItemMeta();
            meta.setOwner(owner);
            item.setItemMeta(meta);
        }catch(ClassCastException expected){}
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level){
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(enchantment, level, true);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments){
        item.addEnchantments(enchantments);
        return this;
    }

    public ItemBuilder setUnbreakable(boolean hideUnbreakable){
        ItemMeta meta = item.getItemMeta();
        meta.spigot().setUnbreakable(true);
        if(hideUnbreakable == true) {
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        }
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(String... lore){
        ItemMeta meta = item.getItemMeta();
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder removeLore(String line){
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>(meta.getLore());
        if(!lore.contains(line)) {
            return this;
        }
        lore.remove(line);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder removeLore(int index){
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>(meta.getLore());
        if(index <  0 || index > lore.size()) {
            return this;
        }
        lore.remove(index);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder addLore(String line){
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        if(meta.hasLore()) {
            lore = new ArrayList<>(meta.getLore());
        }
        lore.add(line);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder addLore(String line, int pos){
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>(meta.getLore());
        lore.set(pos, line);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return this;
    }

    @Deprecated
    public ItemBuilder setWoolColor(DyeColor color){
        if(!item.getType().equals(Material.WOOL)) {
            return this;
        }
        this.item.setDurability(color.getData());
        return this;
    }

    public ItemBuilder setLeatherArmorColor(Color color){
        try{
            LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
            meta.setColor(color);
            item.setItemMeta(meta);
        }catch(ClassCastException exception){

        }
        return this;
    }

    public ItemStack build(){
        return item;
    }
}