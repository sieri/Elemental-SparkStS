package elementalSpark.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import elementalSpark.ElementalSpark;
import elementalSpark.actions.ChangeElementalStanceAction;
import elementalSpark.characters.TheSpark;
import elementalSpark.modifiers.ElementLimiterModifier;
import elementalSpark.powers.FireOfTheForgePower;
import elementalSpark.powers.StopFirePower;
import elementalSpark.stances.ElementAbstractStance;

import static elementalSpark.ElementalSpark.makeCardPath;


public class FinalFlame extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = ElementalSpark.makeID(FinalFlame.class.getSimpleName());
    public static final String IMG = makeCardPath("FinalFlame.png");


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheSpark.Enums.COLOR_WHITE;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 2;

    private static final int DAMAGE = 50;
    private static final int UPGRADE_PLUS_DMG = 75;

    // /STAT DECLARATION/


    public FinalFlame() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;

        CardModifierManager.addModifier(this, new ElementLimiterModifier(ElementAbstractStance.ElementType.Fire));
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));

        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new StopFirePower(4)));

        AbstractDungeon.actionManager.addToBottom(
                new ChangeElementalStanceAction(ElementAbstractStance.ElementType.None)
        );

    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
