SHAPES = GObj.class GLine.class GOval.class GPoly.class GRect.class GText.class
CLASSES = JabaGator.class JBCont.class JBModel.class JBView.class M.class $(SHAPES)

all:	$(CLASSES)

.include <bsd.java.mk>

JCC = guavac
