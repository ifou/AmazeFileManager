/*
 * Copyright (C) 2014 Arpit Khurana <arpitkh96@gmail.com>, Vishal Nehra <vishalmeham2@gmail.com>
 *
 * This file is part of Amaze File Manager.
 *
 * Amaze File Manager is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.amaze.filemanager.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.Toast;

import com.amaze.filemanager.services.DeleteTask;
import com.amaze.filemanager.services.asynctasks.ZipHelperTask;

import java.io.File;
import java.util.ArrayList;

public class ZipViewer extends ListFragment {

    String s;
    public File f;
    public ArrayList<File> files;
    public Boolean results;
    public String current;
    private Main ma;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        s = getArguments().getString("path");
        f = new File(s);
        new ZipHelperTask(this, 0).execute(f);
        files = new ArrayList<File>();
        results = false;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (files.size()==1) {

            new DeleteTask(getActivity().getContentResolver(), ma, getActivity()).execute(files);
        }
    }

    public void goBack() {

        new ZipHelperTask(this, 2, current).execute(f);
    }
}
