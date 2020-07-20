package elementalSpark.actions;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ConvertToRockShieldAction extends AbstractGameAction {

    public ConvertToRockShieldAction()
    {
        this.duration = 0;
    }

    @Override
    public void update() {
        int block = AbstractDungeon.player.currentBlock;

        addToTop(new RemoveAllBlockAction(AbstractDungeon.player,AbstractDungeon.player));

        addToTop(new AddTemporaryHPAction(AbstractDungeon.player, AbstractDungeon.player, block));

        isDone= true;
    }
}
