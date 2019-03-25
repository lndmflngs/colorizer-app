package com.lndmflngs.colorizer.ui.result

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.library.baseAdapters.BR
import com.lndmflngs.colorizer.R
import com.lndmflngs.colorizer.ViewModelProviderFactory
import com.lndmflngs.colorizer.databinding.FragmentResultBinding
import com.lndmflngs.colorizer.extensions.getViewModel
import com.lndmflngs.colorizer.extensions.lockOrientation
import com.lndmflngs.colorizer.extensions.startPickImage
import com.lndmflngs.colorizer.extensions.unlockOrientation
import com.lndmflngs.colorizer.ui.base.BaseFragment
import javax.inject.Inject

class ResultFragment : BaseFragment<FragmentResultBinding, ResultViewModel>(), ResultNavigator {

  @Inject
  lateinit var factory: ViewModelProviderFactory

  override val bindingVariable: Int = BR.viewModel
  override val layoutId: Int = R.layout.fragment_result
  override val viewModel: ResultViewModel by lazy { getViewModel<ResultViewModel>(factory) }

  override val hasOptionMenu: Boolean = true
  override val menuId: Int? = R.menu.menu_result

  private val menuItems = arrayOf(R.id.change, R.id.share)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel.navigator = this
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    (activity as AppCompatActivity).lockOrientation()
    if (savedInstanceState != null) {
      viewModel.imageSource.set(savedInstanceState.getString(BUNDLE_RESULT_IMG_SOURCE)!!)
      showResult()
    } else {
      val byteArray = arguments?.getByteArray(ARGUMENT_PICKED_IMG)!!
      viewModel.sendImageToColorize(byteArray)
    }
  }

  override fun onSaveInstanceState(outState: Bundle) {
    outState.putString(BUNDLE_RESULT_IMG_SOURCE, viewModel.imageSource.get())
    super.onSaveInstanceState(outState)
  }

  override fun onPrepareOptionsMenu(menu: Menu) {
    super.onPrepareOptionsMenu(menu)
    menuItems.forEach { menu.findItem(it).isEnabled = viewModel.isMenuActionsEnabled.get() }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.change -> {
        (activity as AppCompatActivity).startPickImage()
        true
      }
      R.id.share -> {
        viewModel.shareImage(viewDataBinding.resultImageView)
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

  override fun startShareImage(uri: Uri) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
      putExtra(Intent.EXTRA_STREAM, uri)
      type = "image/*"
    }
    startActivity(Intent.createChooser(shareIntent, getString(R.string.title_share_via)))
  }

  override fun showResult() {
    (activity as AppCompatActivity).unlockOrientation()
    activity?.invalidateOptionsMenu() // update menu states
  }

  override fun handleError(throwable: Throwable) {
    Log.d(TAG, throwable.message)
  }

  companion object {
    const val TAG = "ResultFragment"

    private const val ARGUMENT_PICKED_IMG = "ResultFragment:img"
    private const val BUNDLE_RESULT_IMG_SOURCE = "ResultFragment:resultSrc"

    fun newInstance(imgData: ByteArray): ResultFragment {
      val fragment = ResultFragment()
      val args = Bundle()
      args.putByteArray(ARGUMENT_PICKED_IMG, imgData)
      fragment.arguments = args
      return fragment
    }
  }

}