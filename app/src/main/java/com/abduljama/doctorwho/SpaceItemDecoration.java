package com.abduljama.doctorwho;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by abdul on 12/26/15.
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private final int mSpace;
    public SpaceItemDecoration(int space) {
        this.mSpace = space;
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.bottom = mSpace;
        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildAdapterPosition(view) == 0)
            outRect.top = mSpace;
    }
}