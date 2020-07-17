package elementalSpark.modifiers;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import static elementalSpark.ElementalSpark.makeID;

public class BasicAdaptiveAttackModifier extends AbstractAdaptiveModifier{

    private int earthBlockAmount=2;

    private int additionalBlock = 0;


    public BasicAdaptiveAttackModifier(int earthBlockAmount)
    {
        super();

        this.earthBlockAmount = earthBlockAmount;

    }

    @Override
    public float modifyBlock(float block, AbstractCard card) {
        return block+additionalBlock;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new BasicAdaptiveAttackModifier(earthBlockAmount);
    }


    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        switch (currentElement) {
            case Fire:
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAction(target, new DamageInfo(AbstractDungeon.player, card.damage, card.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                break;
            case Earth:
                AbstractDungeon.actionManager.addToBottom(
                        new GainBlockAction(AbstractDungeon.player, card.block)
                );
                break;
            case Water:
                AbstractDungeon.actionManager.addToBottom(
                        new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, 1, false));
                AbstractDungeon.actionManager.addToBottom(
                        new DrawCardAction(1));

                break;
            case Air:
                AbstractDungeon.actionManager.addToBottom(
                        new ApplyPowerAction(target, AbstractDungeon.player, new WeakPower(AbstractDungeon.player,1,false)));
                break;
        }

    }



    @Override
    protected void earth() {
       additionalBlock = earthBlockAmount;
    }

    @Override
    protected void air() {

    }

    @Override
    protected void water() {

    }

    @Override
    protected void fire() {

    }

    @Override
    protected void exit_earth() {
        additionalBlock = 0;
    }

    @Override
    protected void exit_air() {

    }

    @Override
    protected void exit_water() {

    }

    @Override
    protected void exit_fire() {

    }
}
