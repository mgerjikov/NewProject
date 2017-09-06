package com.example.android.newproject.Listeners;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.android.newproject.R;

/**
 * Created by Martin on 4.9.2017 г..
 */

public class CoinClicked {


    private RecyclerView mRecyclerView;

    private OnCoinClickedListener mOnCoinClickedListener;


    // НАМИРАНЕ НА   ** КЛИКНАТОТО **

    // НАЧАЛО:

    // AKO View.OnClickListener-a, ТОЕСТ mOnClickListener, НЕ Е null , ТОЕСТ Е КЛИКНАТО -> правим променлива holder
    // и питаме mRecyclerView за КЛИКНАТОТО МЯСТО (view). ЗА ЦЕЛТА ИЗПОЛЗВАМЕ getChildViewHolder и по този начин намираме КОНКРЕТНОТО view, което e
    // КЛИКНАТО. ТОВА view представлява променливата holder !

    // ПОСЛЕ ИЗПОЛЗВАМЕ ПРОМЕНЛИВАТА holder (ТОВА КЛИКНАТО view) В ИНТЕРФЕЙСА OnCoinClickedListener, ЗА ДА НАМЕРИМ ПОЗИЦИЯТА МУ В АДАПТЕРА
    // ЗА ЦЕЛТА ИЗПОЛЗВАМЕ ПРАЗНИЯ МЕТОД (В ИНТЕРФЕЙСА) onCoinClicked , КОЙТО ВЗИМА ЗА ПАРАМЕТРИ ЛИСТА, ЧИСЛОВАТА ПОЗИЦИЯ И КОНКРЕТНОТО КЛИКНАТО VIEW
    // НАМИРАНЕТО НА ПОЗИЦИЯТА СТАВА, ЧРЕЗ ИЗПОЛЗВАНЕТО НА МЕТОДА getAdapterPosition() върху кликнатото VIEW, което е holder !!!
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mOnCoinClickedListener != null){
                // ask the RecyclerView for the viewHolder of this view.
                // then use it to get the position for the adapter
                RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(view);
                mOnCoinClickedListener.onCoinClicked(mRecyclerView, holder.getAdapterPosition(),view);
            }
        }
    };


    // ****************************  АВТОМАТИЧНО ДОБАВЯНЕ НА LISTENERS  ****************************

    // ВСЕКИ ПЪТ, КОГАТО ИМАМЕ НОВО VIEW В ЛИСТА (RECYCLERVIEW-ТО) ДОБАВЯМЕ КЛИК LISTENER

    // ЗА ЦЕЛТА ИЗПОЛЗВАМЕ On Child Attach State Change Listener, НА КОЙТО В СЛУЧАЯ Е ДАДЕНО ИМЕТО mAttachListener

    // OVERRIDE-ВАМЕ onChildViewAttachedToWindow МЕТОДА, ТАКА ЧЕ ВСЕКИ ПЪТ, КОГАТО ИМА НОВО VIEW,
    // АКО НЯМА ПРИКАЧЕН LISTENER ДА ПРИКАЧАМЕ ТАКЪВ (mOnClickListener)

    // СЪЩОТО ПРАВИМ И ЗА ДЪЛГО КЛИКВАНЕ (АКО ИМАМЕ ТАКАВА ФУНКЦИОНАЛНОСТ)
    private RecyclerView.OnChildAttachStateChangeListener mChildAttachListener = new RecyclerView.OnChildAttachStateChangeListener() {
                @Override
                public void onChildViewAttachedToWindow(View view) {
                    // every time a new child view is attached add click listeners to it
                    if (mOnCoinClickedListener != null){
                        view.setOnClickListener(mOnClickListener);
                    }
                }

                @Override
                public void onChildViewDetachedFromWindow(View view) {

                }
    };

    // ****************************  CONSTRUCTOR / КОНСТРУКТОР  ****************************

    // НЕОБХОДИМ КОНСТРУКТОР, ЗАЩОТО БЕЗ НЕГО НЯМА ДА МОЖЕМ ДА
    // ИЗПОЛЗВАМЕ КОД КАТО : CoinClicked clicked = ...


    public CoinClicked(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        // the ID must be declared in XML, used to avoid
        // replacing the ItemClickSupport without removing
        // the old one from the RecyclerView
        mRecyclerView.setTag(R.id.recycler_view, this);
        mRecyclerView.addOnChildAttachStateChangeListener(mChildAttachListener);
    }

    // ****************************  ДОБАВЯНЕ  ****************************

    public static CoinClicked addTo(RecyclerView view) {
        // if there's already an ItemClickSupport attached
        // to this RecyclerView do not replace it, use it
        CoinClicked support = (CoinClicked) view.getTag(R.id.recycler_view);
        if (support == null) {
            support = new CoinClicked(view);
        }
        return support;
    }

    // ****************************  ПРЕМАХВАНЕ  ****************************

    public static CoinClicked removeFrom(RecyclerView view) {
        CoinClicked support = (CoinClicked) view.getTag(R.id.recycler_view);
        if (support != null) {
            support.detach(view);
        }
        return support;
    }


    public CoinClicked setOnItemClickListener(OnCoinClickedListener listener) {
        mOnCoinClickedListener = listener;
        return this;
    }


    private void detach(RecyclerView view) {
        view.removeOnChildAttachStateChangeListener(mChildAttachListener);
        view.setTag(R.id.recycler_view, null);
    }


    /**
     **********************  INTERFACE  **********************
     */
    public interface OnCoinClickedListener{
        void onCoinClicked(RecyclerView recyclerView, int position, View view);
    }
}
