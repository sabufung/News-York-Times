package coderschool.com.java.newyorktimes.activity.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import org.parceler.Parcels;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import coderschool.com.java.newyorktimes.R;
import coderschool.com.java.newyorktimes.databinding.FragmentFilterBinding;
import coderschool.com.java.newyorktimes.models.FilterSettings;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FilterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FilterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FilterFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    @BindView(R.id.snSort)
    Spinner snSort;
    @BindView(R.id.cbArt)
    CheckBox cbArt;
    @BindView(R.id.cbFashion)
    CheckBox cbFashion;
    @BindView(R.id.cbSport)
    CheckBox cbSport;
    Button btnFilter;
    FragmentFilterBinding binding;


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String FILTER = "filter";



    // TODO: Rename and change types of parameters
    private FilterSettings filter;

    private OnFragmentInteractionListener mListener;

    public FilterFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FilterFragment newInstance(FilterSettings filter) {
        FilterFragment fragment = new FilterFragment();
        Bundle args = new Bundle();
        args.putParcelable(FILTER, Parcels.wrap(filter));


        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            filter = Parcels.unwrap(getArguments().getParcelable(FILTER));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_filter,container,false);
        View view = binding.getRoot();
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(FilterSettings filter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cbArt = binding.cbArt;
//        cbFashion = (CheckBox) view.findViewById(R.id.cbFashion);
//        cbSport = (CheckBox) view.findViewById(R.id.cbSport);
        Log.d("BUU",filter.getSortType() + "");
        btnFilter = binding.btnFilter;
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onFragmentInteraction(filter);
                dismiss();
            }
        });
        binding.setFilter(filter);
//        cbFashion.setChecked(true);
//        cbSport.setChecked(true);

    }
}
