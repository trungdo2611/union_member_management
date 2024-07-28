package com.example.qldv;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.qldv.model.DoanVien;
import com.example.qldv.model.Note;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Note_Activity extends AppCompatActivity {
    private static final String PREFS_NAME = "NotePrefs";
    private static final String KEY_NOTE_COUNT = "NoteCount";
    private LinearLayout notesContainer;
    private List<Note> noteList;
    String iddv;
    Button btnBack,saveButton;
    String ipAddress = "192.168.59.1";
    EditText contentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        notesContainer = findViewById(R.id.notesContainer);
        saveButton = findViewById(R.id.saveButton);
        btnBack = findViewById(R.id.btnBack);
        iddv = getIntent().getStringExtra("id");

        noteList = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(Note_Activity.this);
        String url = "http://" + ipAddress + ":8080/sotay/listSotay?idDV=" + iddv;
        Log.d("urllocal", url);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject item = response.getJSONObject(i);
                        int id_noidung = item.getInt("id");
                        String noiDung = item.getString("noiDung");
                        String idString = String.valueOf(id_noidung);
                        Note note = new Note(noiDung, idString);
                        noteList.add(note);
                    }
                    loadNotesFromPreferences();
                    displayNotes();
                } catch (JSONException ex) {
                    ex.printStackTrace();
                    Toast.makeText(Note_Activity.this, "Lỗi xử lý dữ liệu JSON", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", "Lỗi khi gửi yêu cầu GET: " + error.toString());
                Toast.makeText(Note_Activity.this, "Lỗi khi gửi yêu cầu GET", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonArrayRequest);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote(iddv);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshNoteList();
                    }
                }, 1000); // Thời gian trễ là 1000ms (1 giây)
            }
        });


    }
    private void LoadNote()
    {
        noteList.clear();
        notesContainer.removeAllViews();
        RequestQueue queue = Volley.newRequestQueue(Note_Activity.this);
        String url = "http://" + ipAddress + ":8080/sotay/listSotay?idDV=" + iddv;
        Log.d("urllocal", url);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject item = response.getJSONObject(i);
                        int id_noidung = item.getInt("id");
                        String noiDung = item.getString("noiDung");
                        String idString = String.valueOf(id_noidung);
                        Note note = new Note(noiDung, idString);
                        noteList.add(note);

                        createNoteView(note);
                    }
                    for (int i = 0; i < noteList.size(); i++) {
                        Note currentNote = noteList.get(i);
                        Log.d("DoanVien", "Index " + i + ": " + currentNote.getContent() + ", " + currentNote.getId());
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                    Toast.makeText(Note_Activity.this, "Lỗi xử lý dữ liệu JSON", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error != null && error.getMessage() != null) {
                    Log.d("Error thong tin", error.getMessage());
                    // Hiển thị thông báo Toast khi tên đăng nhập hoặc mật khẩu không chính xác
                    Toast.makeText(Note_Activity.this, "thất bại", Toast.LENGTH_LONG).show();
                } else {
                    Log.d("Error thong tin", "Unknown error");
                    Toast.makeText(Note_Activity.this, "thất bại", Toast.LENGTH_LONG).show();
                }
            }
        });
        queue.add(jsonArrayRequest);
    }

    private void displayNotes() {
        for (Note note : noteList) {
            createNoteView(note);
        }
    }

    private void loadNotesFromPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int noteCount = sharedPreferences.getInt(KEY_NOTE_COUNT, 0);

        if (noteList.isEmpty()) { // Chỉ load từ SharedPreferences nếu noteList là trống
            for (int i = 0; i < noteCount; i++) {
                String content = sharedPreferences.getString("note_content_" + i, "");
                Note note = new Note();
                note.setContent(content);
                noteList.add(note);
            }
        }
    }

    private void saveNote(String iddv) {
        contentEditText = findViewById(R.id.contentEditText);
        String content = contentEditText.getText().toString();

        if (!content.isEmpty()) {
            // Kiểm tra xem ghi chú đã tồn tại trong danh sách chưa
            boolean isNoteExists = false;
            for (Note note : noteList) {
                if (note.getContent().equals(content)) {
                    isNoteExists = true;
                    break;
                }
            }

            if (!isNoteExists) { // Nếu ghi chú chưa tồn tại, thêm mới
                RequestQueue queue = Volley.newRequestQueue(Note_Activity.this);
                String url = "http://" + ipAddress + ":8080/sotay/create";
                HashMap<String, String> params = new HashMap<>();
                params.put("noiDung", contentEditText.getText().toString());
                params.put("doanVien_id", iddv);

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        refreshNoteList();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error != null && error.getMessage() != null) {
                            Log.d("Error thong tin 1", error.getMessage());
                        } else {
                            Log.d("Error thong tin 1", "Unknown error");
                        }
                    }
                });
                queue.add(jsonObjectRequest);
            } else {
                // Nếu ghi chú đã tồn tại, thông báo hoặc thực hiện hành động phù hợp
                Toast.makeText(Note_Activity.this, "Ghi chú đã tồn tại", Toast.LENGTH_SHORT).show();
            }
            clearInputFields();
        }
    }



    private void clearInputFields() {

        EditText contentEditText = findViewById(R.id.contentEditText);


        contentEditText.getText().clear();
    }

    private void createNoteView(final Note note) {
        View noteView = getLayoutInflater().inflate(R.layout.note_item, null);
        TextView contentTextView = noteView.findViewById(R.id.contentTextView);
        contentTextView.setText(note.getContent());
        noteView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDeleteDialog(note);
                return true;
            }
        });
        notesContainer.addView(noteView);
    }

    private void showDeleteDialog(final Note note) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Bạn đã hoàn thành việc này chưa."); //Xóa
        builder.setPositiveButton("Đã xong", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Lấy id của ghi chú để thực hiện xóa
                String noteId = note.getId();
                if (noteId != null) {
                    RequestQueue queue = Volley.newRequestQueue(Note_Activity.this);
                    String url = "http://" + ipAddress + ":8080/sotay/delete/" + noteId;
                    StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(Note_Activity.this, "Thành Công", Toast.LENGTH_LONG).show();
                            deleteNoteAndRefresh(note); // Xóa ghi chú sau khi xóa thành công từ cơ sở dữ liệu
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Note_Activity.this, "Thất bại", Toast.LENGTH_LONG).show();
                        }
                    });
                    queue.add(stringRequest);
                } else {
                    Toast.makeText(Note_Activity.this, "Không thể xóa ghi chú", Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("Chưa xong", null);
        builder.show();
    }

    private void deleteNoteAndRefresh(Note note) {
        noteList.remove(note);
        saveNotesToPreferences();
        refreshNoteViews();
    }

    private void refreshNoteViews() {
        notesContainer.removeAllViews();
        displayNotes();
    }

    private void refreshNoteList() {
        // Xóa tất cả các ghi chú hiện tại trong noteList
        noteList.clear();

        // Thực hiện lại yêu cầu GET để lấy danh sách ghi chú mới từ máy chủ
        RequestQueue queue = Volley.newRequestQueue(Note_Activity.this);
        String url = "http://" + ipAddress + ":8080/sotay/listSotay?idDV=" + iddv;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject item = response.getJSONObject(i);
                        int id_noidung = item.getInt("id");
                        String noiDung = item.getString("noiDung");
                        String idString = String.valueOf(id_noidung);
                        Note note = new Note(noiDung, idString);
                        noteList.add(note);
                    }
                    for (int i = 0; i < noteList.size(); i++) {
                        Note currentNote = noteList.get(i);
                        Log.d("Note", "Index " + i + ": " + currentNote.getContent() + ", " + currentNote.getId());
                    }
                    // Sau khi tải lại danh sách ghi chú từ cơ sở dữ liệu, lưu danh sách mới vào SharedPreferences
                    saveNotesToPreferences();
                    // Làm mới giao diện hiển thị
                    refreshNoteViews();
                } catch (JSONException ex) {
                    ex.printStackTrace();
                    Toast.makeText(Note_Activity.this, "Lỗi xử lý dữ liệu JSON", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", "Lỗi khi gửi yêu cầu GET: " + error.toString());
                Toast.makeText(Note_Activity.this, "Lỗi khi gửi yêu cầu GET", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonArrayRequest);
    }

    private void saveNotesToPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_NOTE_COUNT, noteList.size());
        for (int i = 0; i < noteList.size(); i++) {
            Note note = noteList.get(i);
            editor.putString("Nội dung cần làm" + i, note.getContent());
        }
        editor.apply();
    }

}