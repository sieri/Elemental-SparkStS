package elementalSpark.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import elementalSpark.ElementalSpark;
import elementalSpark.actions.MonsterNextMove;
import elementalSpark.characters.TheSpark;
import elementalSpark.modifiers.ElementLimiterModifier;
import elementalSpark.stances.ElementAbstractStance.ElementType;

import static elementalSpark.ElementalSpark.makeCardPath;


public class Destabilize extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = ElementalSpark.makeID(Destabilize.class.getSimpleName());
    public static final String IMG = makeCardPath("Destabilize.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheSpark.Enums.COLOR_WHITE;


    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;

    // /STAT DECLARATION/


    public Destabilize() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        CardModifierManager.addModifier(this, new ElementLimiterModifier(ElementType.Air));
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot( new MonsterNextMove(m));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
