package com.moataz.afternoonhadeeth.ui.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.data.source.ZekrSource
import com.moataz.afternoonhadeeth.ui.view.activity.MainActivity
import org.joda.time.DateTime
import org.joda.time.chrono.IslamicChronology
import java.util.*

class ZekrWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
) {
    // displaying the Islamic date and time
    val locale = Locale("ar")
    val dateTimeArabic = DateTime.now().plusDays(1).withChronology(IslamicChronology.getInstance())
        .toString("dd-MMMM-YYYY", locale)
    val dateName = DateTime.now().toString("E", locale)

    // opining the app when click on the appWidget
    val intent = Intent(context, MainActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.zekr_widget)

    views.setTextViewText(R.id.appwidget_text, ZekrSource().zekrList())

    views.setTextViewText(R.id.date_time, dateTimeArabic)
    views.setTextViewText(R.id.date_name, dateName)

    views.setOnClickPendingIntent(R.id.zekr_layout, pendingIntent);

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}