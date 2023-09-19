#include <bits/stdc++.h>

using namespace std;

struct segTree {
    int N;
    vector<int> sg;

    void init(int n) {
        N = 1;
        while (N < n) { N <<= 1; }
        sg.resize(N << 1);
    }

    int merge(int a, int b) {
        return a + b;
    }

    void updatePoint(int idx, int val) {
        idx += N - 1;
        sg[idx] += val;
        while (idx > 1) {
            idx >>= 1;
            sg[idx] = merge(sg[idx << 1], sg[(idx << 1) | 1]);
        }
    }

    int query(int l, int r) {
        return query(1, 1, N, l, r);
    }

    int query(int node, int s, int e, int l, int r) {
        if (s > r || e < l)
            return 0;
        if (s >= l && e <= r) {
            return sg[node];
        }
        int mid = (s + e) >> 1;
        int left = node << 1, right = left | 1;
        return merge(query(left, s, mid, l, r), query(right, mid + 1, e, l, r));
    }

};

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    int n;
    cin >> n;
    vector<vector<int>> allElem;
    for (int i = 1; i <= n; i++) {
        int x;
        cin >> x;
        allElem.push_back({x, -1, i});
    }
    int q;
    cin >> q;
    for (int i = 0; i < q; i++) {
        int l, r, k;
        cin >> l >> r >> k;
        allElem.push_back({k, l, r, i});
    }
    sort(allElem.begin(), allElem.end());
    segTree sg;
    sg.init(n);
    vector<int> ans(q);
    for (int i = (int) allElem.size() - 1; ~i; i--) {
        if (~allElem[i][1]) {
            ans[allElem[i][3]] = sg.query(allElem[i][1], allElem[i][2]);
        } else {
            sg.updatePoint(allElem[i][2], 1);
        }
    }
    for (int i = 0; i < q; i++) {
        cout << ans[i] << "\n";
    }
}