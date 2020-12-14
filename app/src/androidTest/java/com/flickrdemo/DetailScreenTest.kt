package com.flickrdemo

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.flickrdemo.view.detail.DetailActivity
import com.flickrdemo.view.home.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class DetailScreenTest {

    @get:Rule
    public val mActivityRule: ActivityTestRule<DetailActivity> =
        ActivityTestRule(DetailActivity::class.java)

    @Test
    fun existImageView() {
        onView(withId(R.id.ivImage)).perform(click())
    }
}