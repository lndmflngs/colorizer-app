package com.lndmflngs.colorizer.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.FragmentManager
import com.lndmflngs.colorizer.BuildConfig
import com.lndmflngs.colorizer.R
import com.lndmflngs.colorizer.ViewModelProviderFactory
import com.lndmflngs.colorizer.databinding.DialogAboutBinding
import com.lndmflngs.colorizer.extensions.getViewModel
import com.lndmflngs.colorizer.ui.base.BaseDialog
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class AboutDialog : BaseDialog<DialogAboutBinding, AboutViewModel>(), AboutCallback {

  @Inject
  lateinit var factory: ViewModelProviderFactory

  override val bindingVariable: Int = BR.viewModel
  override val layoutId: Int = R.layout.dialog_about
  override val viewModel: AboutViewModel by lazy { getViewModel<AboutViewModel>(factory) }

  override val fragmentTag: String = "AboutDialog"

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // perform dataBinding
    viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
    AndroidSupportInjection.inject(this) // performDependencyInjection
    viewDataBinding.viewModel = viewModel
    viewModel.navigator = this
    viewModel.appVersion.set("${getString(R.string.title_version)} ${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})")
    return viewDataBinding.root
  }

  override fun dismissDialog() {
    dismissDialog(fragmentTag)
  }

  fun show(fragmentManager: FragmentManager) {
    super.show(fragmentManager, fragmentTag)
  }

  companion object {
    fun newInstance(): AboutDialog {
      return AboutDialog()
    }
  }

}