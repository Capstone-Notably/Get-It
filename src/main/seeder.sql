-- admin user
-- -------------------------------------------------------------------------------------------
insert into users (username, email, password, img_url, phone) VALUES('admin', 'admin@get-it.press','getit','default_user.png', '000');


-- categories
-- -------------------------------------------------------------------------------------------

insert into categories (user_id, name, img_url, preferences) VALUES(1, 'Produce','1produceCat.jpeg','1 2 3');
insert into categories (user_id, name, img_url, preferences) VALUES(1, 'Grains','2grainsCat.jpeg','1 2');
insert into categories (user_id, name, img_url, preferences) VALUES(1, 'Dairy','3dairyCat.jpg','1 2');
insert into categories (user_id, name, img_url, preferences) VALUES(1, 'Meat','4meatCat.jpg','1');
insert into categories (user_id, name, img_url, preferences) VALUES(1, 'Pantry','5pantryCat.jpg','1 2 3');
insert into categories (user_id, name, img_url, preferences) VALUES(1, 'Snacks','6snackCat.jpg','1 2 3');
insert into categories (user_id, name, img_url, preferences) VALUES(1, 'Beverages','7beverageCat.jpg','1 2 3');
insert into categories (user_id, name, img_url, preferences) VALUES(1, 'Household ','8householdCat.jpg','1 2 3');
insert into categories (user_id, name, img_url, preferences) VALUES(1, 'Specialty','9speciatly.jpg','2 3');
insert into categories (user_id, name, img_url, preferences) VALUES(1, 'Custom','10custom.jpg','1 2 3');

-- items
-- --------------------------------------------------------------------------------------------
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('fruitBasic.jpg', 'fruit',1,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('vegetablesBasic.jpg', 'vegetables',1,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('apples.jpg', 'apples',1,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('asparagus.jpg', 'asparagus',1,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('avocado.jpg', 'avocado',1,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('bananas.jpg', 'bananas',1,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('broccoli.jpeg', 'broccoli',1,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('carrots.jpeg', 'carrots',1,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('garlic.jpeg', 'garlic',1,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('lemons.jpg', 'lemons',1,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('lettuce.jpeg', 'lettuce',1,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('spinach.jpeg', 'spinach',1,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('bagels.jpeg', 'bagels',2,1,'1 2');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('bread.jpg', 'bread',2,1,'1 2');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('cereal.jpeg', 'cereal',2,1,'1 2');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('crackers.jpeg', 'crackers',2,1,'1 2');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('pancakeMix.jpeg', 'pancake mix',2,1,'1 2');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('pastas.jpeg', 'pastas',2,1,'1 2');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('riceWhite.jpg', 'white rice',2,1,'1 2');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('rolls.jpeg', 'rolls',2,1,'1 2');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('spaghetti.jpeg', 'spaghetti',2,1,'1 2');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('torillas.jpeg', 'tortillas',2,1,'1 2');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('butter.jpeg', 'butter',3,1,'1 2');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('cheese.jpg', 'cheese',3,1,'1 2');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('cheeseDip.jpeg', 'cheese dip',3,1,'1 2');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('cheeseShredded.jpeg', 'cheese shredded',3,1,'1 2');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('cheese slices', 'cheese slices',3,1,'1 2');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('eggs.jpeg', 'eggs',3,1,'1 2');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('milk.jpg', 'milk',3,1,'1 2');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('parmesanCheese.jpeg', 'parmesan cheese',3,1,'1 2');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('sourCream.jpeg', 'sour cream',3,1,'1 2');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('soyMilk.jpeg', 'soy milk',3,1,'1 2');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('yogurt.jpeg', 'yogurt',3,1,'1 2');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('bacon.jpeg', 'bacon',4,1,'1');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('beef.jpg', 'beef',4,1,'1');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('beefGround.jpeg', 'ground beef',4,1,'1');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('chicken.jpeg', 'chicken',4,1,'1');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('coldCuts.jpeg', 'cold cuts',4,1,'1');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('fish.jpeg', 'fish',4,1,'1');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('meatBasic.jpeg', 'meat',4,1,'1');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('porkChops.jpeg', 'pork chops',4,1,'1');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('salmon_boneless_skinless.jpeg', 'salmon boneless skinless',4,1,'1');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('shrimp.jpeg', 'shrimp',4,1,'1');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('tuna.jpeg', 'tuna',4,1,'1');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('basil_organic.jpeg', 'basil organic',5,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('canGoods.jpeg', 'can goods',5,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('canTomatoes.jpg', 'can tomatoes',5,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('vegetable_broth.jpeg', 'vegetable broth',5,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('flour.jpeg', 'flour',5,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('garlic_powder.jpeg', 'garlic powder',5,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('jelly.jpeg', 'jelly',5,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('ketchup.jpeg', 'ketchup',5,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('lemonPepperSeasoning.jpeg', 'lemon pepper seasoning',5,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('mustard.jpeg', 'mustard',5,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('oliveOil.jpeg', 'olive oil',5,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('onionPowder.jpeg', 'onion powder',5,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('peanutButter.jpeg', 'peanut butter',5,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('pepper.jpeg', 'pepper',5,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('salt.jpeg', 'salt',5,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('seasoningSalt.jpeg', 'seasoning salt',5,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('spices.jpg', 'spices',5,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('cake.jpeg', 'cake',6,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('candy.jpeg', 'candy',6,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('chips.jpeg', 'chips',6,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('cookies.jpeg', 'cookies',6,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('driedFruit.jpeg', 'dried fruit',6,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('iceCream.jpeg', 'ice cream',6,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('jello.jpeg', 'jello',6,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('pretzels.jpeg', 'pretzels',6,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('riceCakes.jpeg', 'rice cakes',6,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('snacksBasic.jpeg', 'snacks',6,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('beverageBasic.jpeg', 'beverages',7,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('beer.jpeg', 'beer',7,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('bottledWater.jpeg', 'bottled water',7,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('coffee.jpeg', 'coffee',7,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('dietSoda.jpeg', 'diet soda',7,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('fruitJuice.jpeg', 'fruit juice',7,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('orangeJuice.jpeg', 'orange juice',7,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('soda.jpeg', 'soda',7,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('sportsDrink.jpeg', 'sports drink',7,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('tea.jpeg', 'tea',7,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('wine.jpeg', 'wine',7,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('batteries.jpeg', 'batteries',8,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('catFood.jpeg', 'cat food',8,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('catLitter.jpeg', 'cat litter',8,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('dogFood.jpeg', 'dog food',8,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('dogTreats.jpeg', 'dog treats',8,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('householdCleaners.jpg', 'household cleaners',8,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('lightBulbs.jpeg', 'light bulbs',8,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('shampoo.jpeg', 'shampoo',8,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('soap.jpeg', 'soap',8,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('sponges.jpeg', 'sponges',8,1,'1 2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('soyMilk.jpeg', 'soy milk',9,1,'2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('coconutMilk.jpeg', 'coconut milk',9,1,'2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('veganCheese.jpeg', 'vegan cheese',9,1,'2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('tofu.jpeg', 'tofu',9,1,'2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('veggieBurger.jpeg', 'veggie burger',9,1,'2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('veggieChicken.jpeg', 'veggie chicken',9,1,'2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('nutritionalYeast.jpeg ', 'nutritional yeast ',9,1,'2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('veganButter.jpeg', 'vegan butter',9,1,'2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('coconutOil.jpeg', 'vegan bread',9,1,'2 3');
insert into items ( img_url, name, category_id, user_id, preferences) VALUES('nonDairyCreamer.jpeg', 'non-dairy creamer',9,1,'2 3');

-- recipes
-- -----------------------------------------------------------------------------------
insert into recipes (name, directions, img_url, time, user_id) VALUES('Roasted Broccoli with Parmesan', 'How to Make It:  1. Place broccoli and garlic into baking pan. Drizzle with oil and toss.  2. Bake at 450°, stirring occasionally, 20 to 25 minutes or until lightly browned. Sprinkle with salt, pepper, and cheese.','1_roasted_broccoli_cauliflower.jpg','Bake: 25 minutes.',1);
insert into recipes (name, directions, img_url, time, user_id) VALUES('Lemon Pepper Chicken', 'How to Make It:  1. Combine all ingredients except chicken in a large ziplock bag.  2. Add chicken to the bag. Seal it shut and chill it in the fridge for half an hour.  3. Heat a grill to medium high. Cook chicken on grill and monitor internal temperature. ','2_lemon_pepper_chicken.jpg','Cook: Until internal temp is 165 degrees F.',1);
insert into recipes (name, directions, img_url, time, user_id) VALUES('Tomato Basil Salmon', 'How to Make It: 1. Preheat oven to 375 degrees F. 2. Line a baking sheet with aluminum foil and spray with nonstick cooking spray.  3. Place the salmon fillets onto the foil, sprinkle with basil, top with tomato slices, drizzle with olive oil, and sprinkle with the Parmesan cheese.  4. Bake in the preheated oven until the salmon is opaque in the center, and the Parmesan cheese is lightly browned on top.  5. Serve with a side of sauteed spinach. ','3_tomato_basil_salmon.jpg','Cook for approx. 20 minutes.',1);
insert into recipes (name, directions, img_url, time, user_id) VALUES('Cilantro Lime Rice - Vegan', 'How to Make It:  1. Combine the rice, chicken broth, salt and ground cumin in a large saucepan or skillet. Bring to a boil over medium-high heat. Cover with lid then reduce heat to medium-low. Let simmer 20 minutes. 2. Remove from heat, but keep lid on. Let stand with lid on for 10 minutes. Remove lid and add in lime juice and chopped cilantro. Fluff with a fork and serve warm.','4_cilantro_lime_rice_vegan.jpg','Cook for approx. 30 minutes',1);
insert into recipes (name, directions, img_url, time, user_id) VALUES('Garlic Chicken', 'How to Make It: 1. Melt butter in a large skillet over medium high heat. 2. Add chicken and sprinkle with garlic powder, seasoning salt and onion powder. 3. Saute approx. 10 to 15 minutes on each side, or until chicken is cooked through and juices run clear.','5_garlic_chicken.jpg','Cook for approx. 25 minutes.',1);

-- recipe_items
-- -----------------------------------------------------------------------------------
insert into recipe_items (recipe_id, quantity, item_id) VALUES(1,'2 cups',7);
insert into recipe_items (recipe_id, quantity, item_id) VALUES(1,'5 cloves',9);
insert into recipe_items (recipe_id, quantity, item_id) VALUES(1,'1 tbsp.',55);
insert into recipe_items (recipe_id, quantity, item_id) VALUES(1,'1 tsp.',59);
insert into recipe_items (recipe_id, quantity, item_id) VALUES(1,'1/2 tsp.',58);
insert into recipe_items (recipe_id, quantity, item_id) VALUES(1,'1 tbsp.',30);
insert into recipe_items (recipe_id, quantity, item_id) VALUES(2,'6 pcs.',37);
insert into recipe_items (recipe_id, quantity, item_id) VALUES(2,'1tsp.',56);
insert into recipe_items (recipe_id, quantity, item_id) VALUES(2,'1 tsp.',53);
insert into recipe_items (recipe_id, quantity, item_id) VALUES(2,'1 tsp.',59);
insert into recipe_items (recipe_id, quantity, item_id) VALUES(2,'1 tsp.',58);
insert into recipe_items (recipe_id, quantity, item_id) VALUES(3,'12 oz.',42);
insert into recipe_items (recipe_id, quantity, item_id) VALUES(3,'1 tbsp.',45);
insert into recipe_items (recipe_id, quantity, item_id) VALUES(3,'1',47);
insert into recipe_items (recipe_id, quantity, item_id) VALUES(3,'1 tbsp.',55);
insert into recipe_items (recipe_id, quantity, item_id) VALUES(3,'2 tbsp.',30);
insert into recipe_items (recipe_id, quantity, item_id) VALUES(4,'1 cup',19);
insert into recipe_items (recipe_id, quantity, item_id) VALUES(4,'2 cups',48);
insert into recipe_items (recipe_id, quantity, item_id) VALUES(4,'1/2 tsp.',59);
insert into recipe_items (recipe_id, quantity, item_id) VALUES(4,'2 tbsp.',10);
insert into recipe_items (recipe_id, quantity, item_id) VALUES(4,'2 tbsp.',2);
insert into recipe_items (recipe_id, quantity, item_id) VALUES(5,'3 tbsp.',23);
insert into recipe_items (recipe_id, quantity, item_id) VALUES(5,'4 pcs.',37);
insert into recipe_items (recipe_id, quantity, item_id) VALUES(5,'2 tsp.',9);
insert into recipe_items (recipe_id, quantity, item_id) VALUES(5,'1 tsp.',60);
insert into recipe_items (recipe_id, quantity, item_id) VALUES(5,'1 tsp.',56);

-- preferences
-- -----------------------------------------------------------------------------------
insert into preferences (name) VALUES('omnivore');
insert into preferences (name) VALUES('vegetarian');
insert into preferences (name) VALUES('vegan');