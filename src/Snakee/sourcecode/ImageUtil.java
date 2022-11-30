package Snakee.sourcecode;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

public class ImageUtil
{
	public static Map<String, Image> images = new HashMap<>();

	static
	{
		// snake
		images.put("snake-head-right", GameUtil.getImage("Snakee/images/snake-head.png"));
		images.put("snake-body", GameUtil.getImage("Snakee/images/snake-body.png"));
		// obstacles
		images.put("0", GameUtil.getImage("Snakee/images/snake-food-kiwi.png"));
		images.put("1", GameUtil.getImage("Snakee/images/snake-food-lemon.png"));
		images.put("2", GameUtil.getImage("Snakee/images/snake-food-litchi.png"));
		images.put("3", GameUtil.getImage("Snakee/images/snake-food-mango.png"));
		images.put("4", GameUtil.getImage("Snakee/images/snake-food-apple.png"));
		images.put("5", GameUtil.getImage("Snakee/images/snake-food-banana.png"));
		images.put("6", GameUtil.getImage("Snakee/images/snake-food-blueberry.png"));
		images.put("7", GameUtil.getImage("Snakee/images/snake-food-cherry.png"));
		images.put("8", GameUtil.getImage("Snakee/images/snake-food-durian.png"));
		images.put("9", GameUtil.getImage("Snakee/images/snake-food-grape.png"));
		images.put("10", GameUtil.getImage("Snakee/images/snake-food-grapefruit.png"));
		images.put("11", GameUtil.getImage("Snakee/images/snake-food-peach.png"));
		images.put("12", GameUtil.getImage("Snakee/images/snake-food-pear.png"));
		images.put("13", GameUtil.getImage("Snakee/images/snake-food-orange.png"));
		images.put("14", GameUtil.getImage("Snakee/images/snake-food-pineapple.png"));
		images.put("15", GameUtil.getImage("Snakee/images/snake-food-strawberry.png"));
		images.put("16", GameUtil.getImage("Snakee/images/snake-food-watermelon.png"));
		images.put("UI-background", GameUtil.getImage("Snakee/images/UI-background.png"));
		images.put("end-scene", GameUtil.getImage("Snakee/images/end-scene.png"));
	}
}
