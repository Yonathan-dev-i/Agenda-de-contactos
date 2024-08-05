import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.redsystemstudio.agenda.databinding.ItemListBinding
import com.redsystemstudio.agenda.models.Personal

class PersonalAdapter(private val dataSet: List<Personal>) :
    RecyclerView.Adapter<PersonalAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val binding = ItemListBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet.getOrNull(position)
        if (item != null) {
            viewHolder.enlazarItem(item)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    class ViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun enlazarItem(p: Personal) {
            binding.tvNombre.text = "${p.nombre} ${p.apellidos}"
            binding.tvEmail.text = p.mail
            binding.tvTelefono.text = p.telefono.toString()
            binding.tvEdad.text = p.edad.toString()
        }
    }
}

