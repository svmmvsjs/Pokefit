package su.pokefit;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class PokedexViewAdapter extends RecyclerView.Adapter<PokedexViewAdapter.PokeHolder>{

    private Pokedex pokedex;

    public PokedexViewAdapter(Pokedex pokedex) {
        this.pokedex = pokedex;
    }

    public static class PokeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView myItem;
        private Entry myEntry;

        public PokeHolder(View view) {
            super(view);
            myItem = view.findViewById(R.id.xml_pokedex_item);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Context context = itemView.getContext();
            Intent showDetails = new Intent(context, DetailActivity.class);
            showDetails.putExtra("pokeInt", myEntry.getNumber());
            showDetails.putExtra("pokeString", myEntry.getSpecies().getName());
            context.startActivity(showDetails);
        }

        public void doText(Entry entry) {
            myEntry = entry;
            myItem.setText(myEntry.getNumber() + "    " + myEntry.getSpecies().getName());
        }
    }

    @Override
    public PokeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflated = LayoutInflater.from(parent.getContext())
                                        .inflate(R.layout.pokedex_item, parent, false);
        return new PokeHolder(inflated);
    }

    @Override
    public void onBindViewHolder(PokeHolder holder, int position) {
        Entry itemEntry = pokedex.getEntries().get(position);
        holder.doText(itemEntry);
    }

    @Override
    public int getItemCount() {
        return pokedex.getEntries().size();
    }
}
