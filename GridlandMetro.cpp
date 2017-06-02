#include <cmath>
#include <cstdio>
/* Determine the number of locations where lampposts can be placed. 
A lamppost can be placed in any cell that is not occupied by a train track.
Each track is denoted by (r,c1,c2) (tracks may overlap)
Sample input -
4 4 3
2 2 3
3 1 4
4 4 4
Output -
9
*/

#include <vector>
#include <iostream>
#include <algorithm>
#include <map>
#include <tuple>
using namespace std;

int main() {
    long long n,m,k;
    cin >> n >> m >> k;
    map<long long, vector<tuple<long long, long long>>> rails;
    for(long long i=0; i<k; i++) {
        int r,c1,c2;
        cin >> r >> c1 >> c2;
        rails[r].push_back(make_tuple(c1,c2));
    }
    
    long long count = 0;
    for (auto const& x : rails) {
        
        long long row = x.first;
        vector<tuple<long long, long long>> rowRails = x.second ;
        sort(rowRails.begin(), rowRails.end(), [](auto &left, auto &right) {
            return get<0>(left) < get<0>(right);
        });
        long long prevEnd = 0;
        for(auto const& y : rowRails) {
            long long start = get<0>(y);
            long long end = get<1>(y);
            
            if(start>prevEnd) {
                count+= start - prevEnd - 1;
            }
            prevEnd = max(end,prevEnd);
        }
        if(m>prevEnd)
            count+= m - prevEnd;
    
    }
    count += (n-rails.size())*m;
    
    cout << count ;
    return 0;
}
