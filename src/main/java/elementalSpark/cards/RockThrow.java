package elementalSpark.cards;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import elementalSpark.ElementalSpark;
import elementalSpark.characters.TheSpark;
import elementalSpark.modifiers.ElementLimiterModifier;
import elementalSpark.stances.ElementAbstractStance;

import static elementalSpark.ElementalSpark.makeCardPath;


public class RockThrow extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = ElementalSpark.makeID(RockThrow.class.getSimpleName());
    public static final String IMG = makeCardPath("RockThrow.png");


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheSpark.Enums.COLOR_WHITE;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    private static final int DAMAGE = 0;
    private static final int UPGRADE_PLUS_DMG = 0;

    // /STAT DECLARATION/


    public RockThrow() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;

        CardModifierManager.addModifier(this,new ElementLimiterModifier(ElementAbstractStance.ElementType.Earth));
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAllEnemiesAction(p , damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_HEAVY)
        );

        AbstractDungeon.actionManager.addToBottom(
                new RemoveAllBlockAction(p, p)
        );
    }

    public void applyPowers() {
        this.baseDamage = AbstractDungeon.player.currentBlock;
        super.applyPowers();
        this.initializeDescription();
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
