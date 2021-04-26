# DiscordBot

Simple discord bot whith Crossout ingame market info

 - developed with Spring boot
 - Hibernate & PostgreSQL included
 - REST services - online
 - Stream API, project Reactor, Jackson etc

HOWTO:

1. Configuration of .properties changes frequently, so I have no idea how I can put sample here without unvalidate smth..
2. Build with mvn clean install
3. Create PostgreSQL database from application property
4. Run .jar
5. Enjoy

Bot online about 24/7
You can check it with discord:
    https://discord.gg/XdRpYnduTb
    
Active command list (command prefix is ";"):
 
 - item "item name" - show short description of item from https://crossoutdb.com/api/v1/items
  
      example: ;item scorp
      
 - itemlist "item category" "item rarity" - show list of items from selected category with selected rarity
  
      example: ;itemlist weapon legendary
      
 - skynet - this is test feature. This command send all text after to remote server with 
    neural network developed with Python 3 and show you it's response.
    
    example: ;skynet who are you?
    
    Unfortunately neural network develop in progress, so you may not get the answer to "The Ultimate Question of Life, the Universe, and Everything".
    
    P.S. neural network trained in Shakespeare's
    
    
  Next updates expected features:
   - microservices. Powered by Docker.
   - JUnit
   - SLF4J
   - other interesting things
    
    
    
    
