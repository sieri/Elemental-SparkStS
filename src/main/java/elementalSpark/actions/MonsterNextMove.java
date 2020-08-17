package elementalSpark.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MonsterNextMove extends AbstractGameAction {
    protected AbstractMonster monster;



    public MonsterNextMove(AbstractMonster monster) {
        this.monster = monster;
    }

    @Override
    public void update() {
        this.monster.rollMove();
        this.monster.createIntent();
        this.isDone = true;
    }
}
