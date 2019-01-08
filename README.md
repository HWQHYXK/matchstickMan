# matchstickMan
  A small JavaFX game, use your wisdom and reaction speed to defeat your opponents!
  
## Platform 环境
[最新版Java](https://www.java.com/zh_CN/)  
[JDK 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
## 开始 To start with  
  这个游戏提供三种模式 This game provide three modes:
  * PVP
  * PVE
  * EVE(Try hard to find this Hidden Mode ;)
## 基本操作 Basic Operations
>P1: Use WAD to control directions, use S to defend, use K to enable your magic ball, use J to attack by balls, use O to rotate your magicStick, use U to drop your stick to the opponent, use L to dash  
****
>P2: Use ←↑→ to control directions, use ↓ to defend, use Backspace to enable your magic ball, use Ctrl to attack by balls, use Comma to rotate your magicStick, use Period to drop your stick to the opponent, use Space to dash  
  
## 一些解释 Some Explanation
* 盾牌可以挡住敌人的球和敌人的转棍和火球的火焰  
Shield can defend your opponent's balls, stick rotating and dragon's fire but cannot defend stick drop.
****
* 用跳可以避免小骷髅的攻击  
Using jump to avoid the skeleton's attack.
****
* 闪避不能躲避火龙火焰但是可以避免其他的伤害  
Dash cannot avoid the dragon's fire but can avoid other means of attacking
****
* 你不能做任何事情当你防御和闪避时  
You cannot do anything when you defend or dash
****
* 你可以在闪避之前开始转棍子以达到更好的效果  
You may rotate the stick before you dash to avoid the damage from others and do damage to others.
****
* 在困难模式，你的敌人会随血量下降而攻击力上升  
In the hard mode, your opponent's madness will rise in inverse proportion with its HP along with the rise of its ATK.
****
* 在地狱模式，你的敌人可以利用两种魔法，小骷髅怪会让他的血量上升，而火龙的火焰会让你持续掉血  
In the Hail mode, your opponent can use two magic, it can use the skeleton to increase its HP and use dragon's fire to damage over time.
****

## Enjoy

# 关于源代码 About the source
>the classes in package matchstickMan is about the game's framework, it mainly use Timeline animation to build the game.  
Its subpackages including image and media are the resources used by the game.
>the classes in package robot is the place where AI lives in.
