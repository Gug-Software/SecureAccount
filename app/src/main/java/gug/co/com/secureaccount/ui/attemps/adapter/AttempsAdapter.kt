package gug.co.com.secureaccount.ui.attemps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import gug.co.com.secureaccount.data.domain.Attemp
import gug.co.com.secureaccount.databinding.RecyclerItemAttempBinding

class AttempsAdapter() :
    ListAdapter<Attemp, AttempsAdapter.AttempViewHolder>(AttempDiffCallback()) {

    override fun onBindViewHolder(holder: AttempViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttempViewHolder {
        return AttempViewHolder.from(
            parent
        )
    }

    class AttempViewHolder private constructor(
        val binding: RecyclerItemAttempBinding
    ) :

        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Attemp) {
            binding.attemp = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): AttempViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerItemAttempBinding.inflate(layoutInflater, parent, false)

                return AttempViewHolder(binding)
            }
        }
    }

}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minumum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class AttempDiffCallback : DiffUtil.ItemCallback<Attemp>() {
    override fun areItemsTheSame(oldItem: Attemp, newItem: Attemp): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Attemp, newItem: Attemp): Boolean {
        return oldItem == newItem
    }
}