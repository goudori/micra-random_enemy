package plugin.micra_tewntyone.command;

import java.util.List;
import java.util.SplittableRandom;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import plugin.micra_tewntyone.Main;

public class EnemyDownCommand implements CommandExecutor {

  private Main main;

  public EnemyDownCommand(Main main) {
    this.main = main;
  }

  @Override
  public boolean onCommand(CommandSender commandSender, Command command, String s,
      String[] strings) {
    if (commandSender instanceof Player player) {
//      体力回復
      player.setHealth(20);
//      スタミナ回復
      player.setFoodLevel(20);
//      飛行許可
      player.setAllowFlight(true);
//      飛行可能
      player.setFlying(true);
//      飛行スピード
      player.setFlySpeed(1f);

      World world = player.getWorld();

      world.spawnEntity(getEnemySpawnLocation(player, world), getEnemy());


    }
    return false;
  }


  /**
   * 敵の出現場所を取得する
   * <p>
   * 出現エリアのX軸とZ軸は自分の位置からプラス、ランダムで-10~9の値が設定 Y軸は、プレイヤーと同じ位置
   *
   * @param player コマンドを実行したプレイヤー
   * @param world  　コマンドを実行したプレイヤーが所属するワールド
   * @return 敵の出現場所
   */
  private Location getEnemySpawnLocation(Player player, World world) {
    //   出現する敵のエリアを判定する
    Location playerlocation = player.getLocation();
    int randomX = new SplittableRandom().nextInt(20) - 10;
    int randomZ = new SplittableRandom().nextInt(20) - 10;

    double x = playerlocation.getX();
    double y = playerlocation.getY();
    double z = playerlocation.getZ();

    return new Location(world, x, y, z);

  }

  /**
   * ランダムで敵を抽選して、その結果の敵を取得
   *
   * @return 敵
   */

  private EntityType getEnemy() {
    List<EntityType> enemyList = List.of(EntityType.ZOMBIE, EntityType.WITHER_SKELETON,
        EntityType.SKELETON);
    int random = new SplittableRandom().nextInt(enemyList.size());
    return enemyList.get(random);
  }
}
